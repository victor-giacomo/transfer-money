package br.com.bank.transfer.controller;

import br.com.bank.transfer.domain.User;
import br.com.bank.transfer.service.UserService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
public class UserController {

    private UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping(path = "/user/document/{document}")
    public ResponseEntity<User> find(@PathVariable String document) {
        return ResponseEntity.ok(userService.find(document));
    }

    @PostMapping(path = "/user")
    public ResponseEntity<User> save(@RequestBody User user) {
        return ResponseEntity.ok(userService.save(user));
    }
}
