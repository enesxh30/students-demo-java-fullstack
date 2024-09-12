package com.sda.student.controller;

import com.sda.student.entity.User;
import com.sda.student.service.UserService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/user")
public class UserController {

    private UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping("/save")
    public ResponseEntity<User> save(@Valid @RequestBody User user) {
        return new  ResponseEntity<User> (userService.save(user), HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<User> updateById(@Valid @RequestBody User user, @PathVariable("id") long id) {
        return ResponseEntity.ok(userService.updateUserById(user, id));
    }

    @GetMapping
    public List<User> findAll() {
        return userService.findAll();
    }

    @GetMapping("/findById/{id}")
    public User findByUserId(@PathVariable("id") long id) {
        return userService.findById(id);
    }

    @DeleteMapping("/deleteById/{id}")
    public String deleteById(@PathVariable("id") long id) {
        userService.deleteUserById(id);
        return "User with id: "+id+" was successfully deleted!";
    }
}
