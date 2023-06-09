package br.com.api.weplant.controllers;

import java.net.URI;
import java.util.List;
import java.util.ArrayList;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import br.com.api.weplant.dto.*;
import br.com.api.weplant.entities.*;
import br.com.api.weplant.services.*;
import jakarta.validation.Valid;

import org.springdoc.core.annotations.ParameterObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;

@RestController
@RequestMapping(value = "/users")
public class UserController {

    @Autowired
    private UserService userService;

    @Autowired
    private GardenService gardenService;

    @Autowired
    private PostService postService;
  
    @Autowired
    private AuthenticationManager manager;

    @Autowired
    private TokenService tokenService;

    @Autowired
    private PasswordEncoder encoder;


//    @GetMapping("/all")
//    public ResponseEntity<List<UserNoProtectedDataDTO>> findAll() {
//        List<User> users = userService.findAll();
//        List<UserNoProtectedDataDTO> usersDTO = users.stream().map(UserNoProtectedDataDTO::new).collect(Collectors.toList());
//        return ResponseEntity.ok().body(usersDTO);
//    }

    @GetMapping("/{id}")
    public ResponseEntity<UserNoProtectedDataDTO> findById(@PathVariable Long id) {
        User user = userService.findById(id);
        return ResponseEntity.ok().body(new UserNoProtectedDataDTO(user));
    }

    @PostMapping("/add")
    public ResponseEntity<User> insert(@RequestBody UserRegisterDTO userRegisterDTO) {
        User user = userService.fromDTORegister(userRegisterDTO);
        user.setPassword(encoder.encode(user.getPassword()));
        userService.insert(user);
        URI uri = ServletUriComponentsBuilder.fromCurrentRequestUri().path("/{id}")
                .buildAndExpand(userService.findAll().size()).toUri();
        return ResponseEntity.created(uri).body(user);
    }

    @PutMapping("/updt/{id}")
    public ResponseEntity<Void> update(@RequestBody @Valid UserNoProtectedDataDTO newUser, @PathVariable Long id) {
        User user = userService.fromDTOResponse(newUser);
        userService.update(user, id);
        return ResponseEntity.noContent().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> updateUserStatus(@PathVariable Long id) {
        userService.delete(id);
        return ResponseEntity.noContent().build();
    }

    @PostMapping("/{id}/garden/add")
    public ResponseEntity<Void> addGarden(@RequestBody GardenDTO gardenDTO, @PathVariable Long id) {
        Garden garden = gardenService.fromDTO(gardenDTO);
        userService.addNewGarden(garden, id);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @GetMapping("/{id}/gardens")
    public ResponseEntity<List<GardenDTO>> findGardenByUserID(@PathVariable Long id) {
        List<Garden> garden = gardenService.findAllByUserId(id);
        return ResponseEntity.ok().body(new ArrayList<>(garden.stream().map(GardenDTO::new).collect(Collectors.toList())));
    }

    @GetMapping(value = "/{id}/posts")
    public ResponseEntity<Page<PostReducedDTO>> findAllUserPostsByUserId(@PathVariable Long id, @ParameterObject @PageableDefault(size = 5) Pageable pageable) {
        return ResponseEntity.ok().body(postService.findAllByUserId(id, pageable).map(PostReducedDTO::new));
    }

    @PostMapping("/login")
    public ResponseEntity<TokenJWT> login(@RequestBody @Valid UserLogin data) {

        UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(
                data.username(),
                data.password());

        Authentication autentication = manager.authenticate(authenticationToken);

        User login = (User) autentication.getPrincipal();

        String tokenJWT = tokenService.gerarToken(login);

        return ResponseEntity.ok().body(new TokenJWT(login.getId(), tokenJWT));
    }

}
