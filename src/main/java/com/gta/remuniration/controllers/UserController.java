package com.gta.remuniration.controllers;

import com.gta.remuniration.entity.User;
import com.gta.remuniration.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController(value = "/users")
public class UserController {

    @Autowired
    UserService userService;

    @PostMapping(value = "/authenticate")
    public ResponseEntity<User> authenticate(
            @RequestBody User user) {
        return ResponseEntity.status(HttpStatus.OK).body(userService.authenticate(user));
    }
}
