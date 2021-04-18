package com.peasch.controller;

import com.peasch.controller.security.config.JwtTokenProvider;
import com.peasch.model.dto.User.UserDto;
import com.peasch.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
public class UserController {
    @Autowired
    private UserService service;

    @Autowired
    private JwtTokenProvider jwt;


    @GetMapping(value = "/users")
    public List<UserDto> getUsers() {
        return service.getUsers();
    }

    @GetMapping("/user/{id}")
    public UserDto getUserById(@PathVariable(value = "id") Integer id) {
        UserDto userDto = service.findById(id);
        return userDto;
    }

    @PostMapping("/user/add")
    public void addUser(@RequestBody UserDto userDto) {
        service.save(userDto);
    }

    @GetMapping("/user/username/{username}")
    public UserDto getUserByUserName(@PathVariable(value = "username") String userName) {
            return service.findUserByUserName(userName);
    }

    @GetMapping("/user/delete")
    public void deleteUser(@RequestBody UserDto userDto, @RequestHeader(name = "Authorization") String token) {
        service.deleteUser(userDto.getUserName());
    }
}
