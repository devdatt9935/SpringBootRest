package com.dev.SpringBootRest.controller;


import java.util.List;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;
import com.dev.SpringBootRest.service.UserService;
import com.dev.dto.UserDto;

import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;

@RestController
@AllArgsConstructor
@RequestMapping("/api/users")
public class UserController {
    private UserService userService;
    @PostMapping
    public ResponseEntity<UserDto> createUser(@RequestBody @Valid UserDto userDto){
        UserDto savedUser = userService.createUser(userDto);
        return new ResponseEntity<>(savedUser,HttpStatus.CREATED);
    }
    
    @GetMapping("/{id}")
    public ResponseEntity<UserDto> getUserById(@PathVariable("id") Long userId) {
        UserDto userDto = userService.getUserById(userId);
        return ResponseEntity.ok(userDto);
    }

    @GetMapping
    public ResponseEntity<List<UserDto>> getAllUsers() {
        List<UserDto> users = userService.getAllUsers();
        return ResponseEntity.ok(users);
    }
    
    @PutMapping("/{id}")
    public ResponseEntity<UserDto> putMethodName(@PathVariable("id") Long userId, @RequestBody @Valid UserDto user) {
        user.setId(userId);
        UserDto updatedUser = userService.updateUser(user);
        return ResponseEntity.ok(updatedUser);
    }
    
    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteUser(@PathVariable("id") Long userId){
        userService.deleteUser(userId);
        return new ResponseEntity<>("user Deleted Successfully",HttpStatus.OK);
    }
}
