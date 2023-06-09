package br.com.api.weplant.services;

import br.com.api.weplant.dto.PostReducedDTO;
import br.com.api.weplant.dto.UserRegisterDTO;
import br.com.api.weplant.dto.UserNoProtectedDataDTO;
import br.com.api.weplant.entities.*;
import br.com.api.weplant.exceptions.NoDataFoundException;
import br.com.api.weplant.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.crypto.bcrypt.BCrypt;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.Calendar;
import java.util.List;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private GardenService gardenService;

    @Autowired
    private AddressService addressService;

    @Autowired
    private PhoneService phoneService;

 
    public List<User> findAll() {
        return userRepository.findAll();
    }

    public User findById(Long id) {
        return userRepository.findById(id)
                .orElseThrow((() -> new NoDataFoundException("User with id " + id + " not found")));
    }

    public User insert(User user) {
        addressService.insert(user.getAddress());
        phoneService.insert(user.getPhone());
        return userRepository.save(user);
    }

    public void update(User user, Long id) {
        User userInDb = findById(id);
        dataUpdate(userInDb, user);
        userRepository.save(userInDb);
        userRepository.flush();
    }

    public void delete(Long id) {
        User user = findById(id);
        user.setPassword(new BCryptPasswordEncoder().encode(user.getPassword()));
        user.setStatus("I");
        userRepository.save(user);
    }

    public void addNewGarden(Garden garden, Long id) {
        User user = findById(id);
        user.addGarden(garden);
        userRepository.save(user);
        userRepository.flush();
        garden.setUser(user);
        gardenService.insert(garden);
    }

//    public Page<PostReducedDTO> findAllUserPostByUserId(Long id, Pageable pageable) {
//        return postService.findAllByUserId(id, pageable).map(PostReducedDTO::new);
//    }

    public void dataUpdate(User userToAtt, User user) {
        String name = (user.getName() != null && user.getName().isEmpty() && user.getName().isBlank()) ? user.getName()
                : userToAtt.getName();
        userToAtt.setName(name);

        String username = (user.getUsername() != null && user.getUsername().isEmpty() && user.getUsername().isBlank())
                ? user.getUsername()
                : userToAtt.getUsername();
        userToAtt.setUsername(username);

        LocalDate birthday = user.getBirthday() != null ? user.getBirthday() : userToAtt.getBirthday();
        userToAtt.setBirthday(birthday);

        Address address = user.getAddress() != null ? user.getAddress() : userToAtt.getAddress();
        if (!address.equals(userToAtt.getAddress())) {
            address.setId(userToAtt.getAddress().getId());
            userToAtt.setAddress(addressService.insert(address));
        }

        Phone phone = user.getPhone() != null ? user.getPhone() : userToAtt.getPhone();
        if (!phone.equals(userToAtt.getPhone())) {
            phone.setId(userToAtt.getPhone().getId());
            userToAtt.setPhone(phoneService.insert(phone));
        }

    }

    public User fromDTORegister(UserRegisterDTO userRegisterDTO) {
        return new User(userRegisterDTO);
    }

    public User fromDTOResponse(UserNoProtectedDataDTO userNoProtectedDataDTO) {
        return new User(userNoProtectedDataDTO);
    }

    public User findByUsername(String username) {
        return userRepository.findByUsername(username)
                .orElseThrow(() -> new NoDataFoundException("Username not found"));
    }

}
