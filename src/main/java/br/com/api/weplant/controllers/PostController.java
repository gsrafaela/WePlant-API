package br.com.api.weplant.controllers;

import br.com.api.weplant.dto.CommentDTO;
import br.com.api.weplant.dto.CommentRegisterDTO;
import br.com.api.weplant.dto.PostReducedDTO;
import br.com.api.weplant.entities.Post;
import br.com.api.weplant.services.PostService;
import jakarta.validation.Valid;
import org.springdoc.core.annotations.ParameterObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.Calendar;
import java.util.List;

@RestController
@RequestMapping(value = "/posts")
public class PostController {

    @Autowired
    private PostService postService;

    @PostMapping("/add/user/{id}")
    public ResponseEntity<Void> insert(@RequestBody @Valid PostReducedDTO postDTO, @PathVariable Long id) {
        postService.insert(new Post(postDTO), id);
        URI uri = ServletUriComponentsBuilder.fromCurrentRequestUri().path("/{id}")
                .buildAndExpand(postService.findAll().size()).toUri();
        return ResponseEntity.created(uri).build();
    }

    @GetMapping("/{id}")
    public ResponseEntity<PostReducedDTO> findById(@PathVariable Long id) {
        return ResponseEntity.ok().body(new PostReducedDTO(postService.findById(id)));
    }

    @GetMapping("/{id}/comments/all")
    private ResponseEntity<Page<CommentDTO>> findAllCommentsByPostId(@PathVariable Long id, @ParameterObject @PageableDefault(size = 5) Pageable pageable) {
        return ResponseEntity.ok().body(postService.findAllCommentsByPostId(id, pageable));
    }

    @PostMapping("/{id}/comments/add")
    private ResponseEntity<Void> addNewComment(@PathVariable Long id, @RequestBody @Valid CommentRegisterDTO commentRegisterDTO) {
        commentRegisterDTO.setDate(Calendar.getInstance());
        postService.addNewComment(id, commentRegisterDTO);
        return ResponseEntity.noContent().build();
    }

    @DeleteMapping("/{id}/del")
    private ResponseEntity<Void> deleteById(@PathVariable Long id) {
        postService.deleteById(id);
        return ResponseEntity.noContent().build();
    }

    @DeleteMapping("/{id}/comments/{commentId}/del")
    private ResponseEntity<Void> deleteCommentById(@PathVariable Long id, @PathVariable Long commentId) {
        postService.deleteCommentById(id);
        return ResponseEntity.noContent().build();
    }

}
