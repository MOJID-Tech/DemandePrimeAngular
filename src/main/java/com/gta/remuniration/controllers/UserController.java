package com.gta.remuniration.controllers;

import com.gta.remuniration.entity.User;
import com.gta.remuniration.entity.UserChangePasswordDTO;
import com.gta.remuniration.entity.ValidationEmailDTO;
import com.gta.remuniration.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
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
    @GetMapping(value = "/{id}")
    public ResponseEntity<User> findById(@PathVariable Integer id) {
        return ResponseEntity.status(HttpStatus.OK).body(Service.findById(id));
    }


    @PostMapping
    public ResponseEntity<User> create(@RequestParam String login, @RequestParam String password,@RequestParam String email, @RequestParam String role) {
        return ResponseEntity.status(HttpStatus.CREATED).body(Service.createUser(login,password,email,role));
    }

    @PutMapping(value = "/{id}")
    public ResponseEntity<User> update(@PathVariable Integer id,@RequestParam String login) {
        return ResponseEntity.status(HttpStatus.OK).body(Service.updateUser(id, login));
    }
    @PostMapping (value = "/{id}/addrole")
    public ResponseEntity<User> addrole(@PathVariable Integer id,@RequestParam String role) {
        return ResponseEntity.status(HttpStatus.OK).body(Service.addrole(id,role));
    }
    @DeleteMapping (value = "/{id}")
    public ResponseEntity<Boolean> removerole(@PathVariable Integer id,@RequestParam String role) {
        return ResponseEntity.status(HttpStatus.OK).body(Service.removerole(id,role));
    }



    @PutMapping(value = "/changepassword")

    public ResponseEntity<User> changePassword(@RequestBody UserChangePasswordDTO user) {
        return ResponseEntity.status(HttpStatus.OK)
                .body(Service.changePassword(getLogin(), user.getCurrentPassword(), user.getNewPassword()));
    }

    @PostMapping("/{id}/reset-password")
    public ResponseEntity<ValidationEmailDTO> resetPassword(@PathVariable Integer id, @RequestBody UserChangePasswordDTO user) {
        return ResponseEntity.status(HttpStatus.OK).body(Service.resetPassword(id, user.getNewPassword()));
    }

    @PostMapping(value = "/{id}/activate")
    public ResponseEntity<User> activate(@PathVariable Integer id) {
        return ResponseEntity.status(HttpStatus.OK).body(Service.setActive(id, true));
    }

    @PostMapping(value = "/{id}/deactivate")
    public ResponseEntity<User> deactivate(@PathVariable Integer id) {
        return ResponseEntity.status(HttpStatus.OK).body(Service.setActive(id, false));
    }
    protected String getLogin() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        return authentication != null ? authentication.getName() : null;
    }

}
