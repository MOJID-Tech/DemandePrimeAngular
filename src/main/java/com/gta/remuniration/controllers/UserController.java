package com.gta.remuniration.controllers;

import com.gta.remuniration.entity.User;
import com.gta.remuniration.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@CrossOrigin ("*")
@RestController
@RequestMapping(value="/users")

public class UserController {

    @Autowired
    UserService Service;
    @GetMapping
    public ResponseEntity<Page<User>> findAll(
            @RequestParam(required = false, defaultValue = "0") int pageIndex,
            @RequestParam(required = false, defaultValue = "6") int size) {
        return ResponseEntity.status(HttpStatus.OK).body(Service.findAll(pageIndex, size));
    }

    @PostMapping(value = "/authenticate")
    public ResponseEntity<User> authenticate(
            @RequestParam String login, @RequestParam String password) {

        return ResponseEntity.status(HttpStatus.OK).body(Service.authenticate(login,password));
    }
}
