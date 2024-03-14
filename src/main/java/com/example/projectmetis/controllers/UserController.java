package com.example.projectmetis.controllers;

import com.example.projectmetis.dto.UserDto;
import com.example.projectmetis.models.User;
import com.example.projectmetis.service.Implements.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

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
        return user == null ?
                new ResponseEntity<>(HttpStatus.BAD_REQUEST) :
                new ResponseEntity<>(new UserDto(user), HttpStatus.OK);
    }
}
