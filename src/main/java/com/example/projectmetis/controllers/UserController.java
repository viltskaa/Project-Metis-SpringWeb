package com.example.projectmetis.controllers;

import com.example.projectmetis.dto.UserDto;
import com.example.projectmetis.models.User;
import com.example.projectmetis.service.Implements.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Objects;


@Controller
@CrossOrigin
@RequestMapping(path = "/user")
public class UserController {
    private final UserService userService;

    public UserController(UserService userService){
        this.userService = userService;
    }

    @PostMapping("/create")
    public ResponseEntity<UserDto> createUser(@RequestParam String name) {
        return new ResponseEntity<>(new UserDto(userService.create(name)), HttpStatus.OK);
    }

    @GetMapping("/get")
    public ResponseEntity<UserDto> getUser(@RequestParam Long id) {
        User user = userService.getById(id);
        // возможно надо поменять BAD_REQUEST на другое
        return user == null ?
                new ResponseEntity<>(HttpStatus.BAD_REQUEST) :
                new ResponseEntity<>(new UserDto(user), HttpStatus.OK);
    }

    @GetMapping("/getByName")
    public ResponseEntity<UserDto> getUser(@RequestParam String name) {
        User user = userService.getByName(name);
        // возможно надо поменять BAD_REQUEST на другое
        return user == null ?
                new ResponseEntity<>(HttpStatus.BAD_REQUEST) :
                new ResponseEntity<>(new UserDto(user), HttpStatus.OK);
    }

    @GetMapping("/all")
    public ResponseEntity<List<UserDto>> getAllUsers() {
        return new ResponseEntity<>(
                userService.getAll().stream().map(UserDto::new).toList()
                , HttpStatus.OK);
    }

    @DeleteMapping("/delete")
    public ResponseEntity<UserDto> deleteUser(@RequestParam Long id) {
        User user = userService.deleteById(id);
        // возможно надо поменять BAD_REQUEST на другое
        return user == null ?
                new ResponseEntity<>(HttpStatus.BAD_REQUEST) :
                new ResponseEntity<>(new UserDto(user), HttpStatus.OK);
    }

    @DeleteMapping("/deleteByName")
    public ResponseEntity<UserDto> deleteUser(@RequestParam String name) {
        User user = userService.deleteByName(name);
        // возможно надо поменять BAD_REQUEST на другое
        return user == null ?
                new ResponseEntity<>(HttpStatus.BAD_REQUEST) :
                new ResponseEntity<>(new UserDto(user), HttpStatus.OK);
    }

    @PatchMapping("/edit")
    public ResponseEntity<UserDto> editUser(@RequestParam Long id,
                                                  @RequestParam String name) {
        UserDto user = new UserDto();
        user.setId(id);
        user.setName(name);

        User userUpd = userService.edit(user);
        return userUpd == null ?
                new ResponseEntity<>(HttpStatus.BAD_REQUEST) :
                new ResponseEntity<>(new UserDto(userUpd), HttpStatus.OK);
    }
}
