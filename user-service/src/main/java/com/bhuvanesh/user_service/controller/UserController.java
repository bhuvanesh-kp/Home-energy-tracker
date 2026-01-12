package com.bhuvanesh.user_service.controller;

import com.bhuvanesh.user_service.dto.UserDto;
import com.bhuvanesh.user_service.service.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/user")
public class UserController {
    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }
    //CRUD CONTROLLERS

    //GET USER BY ID
    @GetMapping("/{id}")
    public ResponseEntity<UserDto> getUserById(@PathVariable(name = "id") Long id){
        UserDto user = userService.getUserById(id);
        if (user == null) return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        return ResponseEntity.ok(user);
    }

    // GET ALL USERS

    /*
    @GetMapping
    public ResponseEntity<List<UserDto>> getAllUsers(){
        List<UserDto> users = userService.getAllUsers();
        return ResponseEntity.ok(users);
    }
    */

    // CREATE USER
    @PostMapping
    public ResponseEntity<UserDto> createUser(@RequestBody UserDto user){
        UserDto created = userService.createNewUser(user);
        return new ResponseEntity<>(created,HttpStatus.CREATED);
    }

    // UPDATING A EXISTING USER
    @PutMapping("/{id}")
    public ResponseEntity<String> updateUser(@PathVariable(name = "id") Long id, @RequestBody UserDto userDto){
        try{
            userService.updateUser(id, userDto);
            return ResponseEntity.ok("User updated");
        }
        catch (IllegalArgumentException ex){
            return new ResponseEntity<>("user not found with the id" + id, HttpStatus.NOT_FOUND);
        }
    }

    // DELETE AN EXISTING USER
    @DeleteMapping("/{id}")
    public ResponseEntity<Void>  deleteUser(@PathVariable(name = "id") Long id){
        try{
            userService.deleteUser(id);
            return ResponseEntity.noContent().build();
        }
        catch (IllegalArgumentException ex){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
}
