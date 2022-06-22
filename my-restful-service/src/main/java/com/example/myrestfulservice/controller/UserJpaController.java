package com.example.myrestfulservice.controller;

import com.example.myrestfulservice.bean.Post;
import com.example.myrestfulservice.bean.User;
import com.example.myrestfulservice.exception.UserNotFoundException;
import com.example.myrestfulservice.jpa.PostRepository;
import com.example.myrestfulservice.jpa.UserRepository;
import com.example.myrestfulservice.vo.ResponseData;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.mvc.WebMvcLinkBuilder;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.validation.Valid;
import java.net.URI;
import java.util.List;
import java.util.Optional;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@RestController
@RequestMapping("/jpa")
public class UserJpaController {

    private UserRepository userRepository;
    private PostRepository postRepository;

    @Autowired
    public UserJpaController(UserRepository userRepository, PostRepository postRepository) {
        this.userRepository = userRepository;
        this.postRepository = postRepository;
    }

    @GetMapping("/users")
    public ResponseEntity retrieveAllUsers() {
        List<User> users = userRepository.findAll();

        ResponseData responseData = ResponseData.builder()
                .count(users == null || users.isEmpty() ? 0 : users.size())
                .users(users)
                .build();

        EntityModel entityModel = EntityModel.of(responseData);
        WebMvcLinkBuilder linkTo = linkTo(methodOn(this.getClass()).retrieveAllUsers());
        entityModel.add(linkTo.withSelfRel());

        return ResponseEntity.ok(entityModel);
    }

    @GetMapping("/users/{id}")
    public ResponseEntity retrieveUserById(@PathVariable(value = "id") int id) {
        // JPA -> 데이터 조회 함수 호출
        Optional<User> user = userRepository.findById(id);

        if (!user.isPresent()) {
            throw new UserNotFoundException("id-" + id);
        }

        // HATEOAS 추가 (전체 사용자 목록 보기)
        EntityModel entityModel = EntityModel.of(user.get());
        WebMvcLinkBuilder linkTo = linkTo(methodOn(this.getClass()).retrieveAllUsers());
        entityModel.add(linkTo.withRel("all-users"));

        return ResponseEntity.ok(entityModel);
    }

    @DeleteMapping("/users/{id}")
    public void deleteUser(@PathVariable(value = "id") int id) {
        userRepository.deleteById(id);
    }

    @PostMapping(value = "/users/{id}")
    public ResponseEntity<User> createUser(@Valid @RequestBody User user) {

        User savedUser = userRepository.save(user);

        URI location = ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(savedUser.getId())
                .toUri();

        return ResponseEntity.created(location).build();
    }

    @PutMapping
    public ResponseEntity<User> updateUserById(@PathVariable(value = "id") int id,
                                               @RequestBody User user) {
        Optional<User> storedUser = userRepository.findById(id);

        if (!storedUser.isPresent()) {
            throw new UserNotFoundException("id-" + id);
        }

        user.setId(id);
        userRepository.save(user); // update sql

//        EntityModel entityModel = EntityModel.of(user.get());
//        WebMvcLinkBuilder linkTo = linkTo(methodOn(this.getClass()).retrieveAllUsers());
//        entityModel.add(linkTo.withRel("all-users"));

        return ResponseEntity.noContent().build();// 204
    }


    @GetMapping("/users/{id}/posts")
    public List<Post> retrieveAllPostsByUser(@PathVariable(value = "id") int id) {
        // JPA -> 데이터 조회 함수 호출
        Optional<User> user = userRepository.findById(id);

        if (!user.isPresent()) {
            throw new UserNotFoundException("id-" + id);
        }

//        // HATEOAS 추가 (전체 사용자 목록 보기)
//        EntityModel entityModel = EntityModel.of(user.get());
//        WebMvcLinkBuilder linkTo = linkTo(methodOn(this.getClass()).retrieveAllUsers());
//        entityModel.add(linkTo.withRel("all-users"));

        return user.get().getPosts();
    }

    @PostMapping(value = "/users/{id}/posts")
    public ResponseEntity<Post> createPost(@PathVariable(value = "id") int id,
                                           @RequestBody Post post) {
        Optional<User> storedUser = userRepository.findById(id);

        if (!storedUser.isPresent()) {
            throw new UserNotFoundException("id-" + id);
        }

        User user = storedUser.get();
        post.setUser(user);

        postRepository.save(post);

        URI location = ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(post.getId())
                .toUri();

        return ResponseEntity.created(location).build();
    }
}
