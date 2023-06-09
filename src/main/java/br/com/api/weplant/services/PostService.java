package br.com.api.weplant.services;

import br.com.api.weplant.dto.CommentDTO;
import br.com.api.weplant.dto.CommentRegisterDTO;
import br.com.api.weplant.entities.Comment;
import br.com.api.weplant.entities.Post;
import br.com.api.weplant.entities.User;
import br.com.api.weplant.exceptions.NoDataFoundException;
import br.com.api.weplant.repositories.CommentRepository;
import br.com.api.weplant.repositories.PostRepository;
import br.com.api.weplant.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PostService {

    @Autowired
    private PostRepository postRepository;

    @Autowired
    private CommentRepository commentRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private UserService userService;

    public Page<Post> findAllByUserId(Long id, Pageable pageable) {
        return postRepository.findAllByUserId(id, pageable);
    }

    public void insert(Post post, Long id) {
        User user = userService.findById(id);
        post.setUser(user);
        postRepository.saveAndFlush(post);
        user.addPost(post);
        userRepository.saveAndFlush(user);
    }

    public Post findById(Long id) {
        return postRepository.findById(id).orElseThrow((() -> new NoDataFoundException("Post with id " + id + " not found")));
    }

    public Page<CommentDTO> findAllCommentsByPostId(Long id, Pageable pageable) {
        return commentRepository.findAllByPostId(id, pageable).map(CommentDTO::new);
    }

    public void addNewComment(Long postId, CommentRegisterDTO commentRegisterDTO) {
        User user = userService.findById(commentRegisterDTO.getUserId());
        Post post = findById(postId);
        Comment comment = new Comment(commentRegisterDTO.getBody(), user, commentRegisterDTO.getDate(), post);
        commentRepository.save(comment);
        post.addComment(comment);
        postRepository.save(post);
        postRepository.flush();
        user.addComment(comment);
        userRepository.saveAndFlush(user);
    }

    public void deleteById(Long id) {
        findById(id);
        postRepository.deleteById(id);
    }

    public void deleteCommentById(Long id) {
        commentRepository.deleteById(id);
    }

    public List<Post> findAll() {
        return postRepository.findAll();
    }
}
