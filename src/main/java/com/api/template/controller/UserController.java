package com.api.template.controller;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.api.template.request.LoginRequest;
import com.api.template.request.UserRequest;
import com.api.template.service.UserServices;

@RestController
@RequestMapping("/api")
public class UserController {
    
   @Autowired
   UserServices userServices;
    
    @PostMapping("/login")
    public ResponseEntity<?> login(@Valid @RequestBody final LoginRequest req) throws Exception {
        return ResponseEntity.ok(userServices.login(req));
    }
    
    @GetMapping("/users")
    public ResponseEntity<?> user() throws Exception {
        return ResponseEntity.ok(userServices.getUser());
    }
    
    @GetMapping("/users/{id}")
    public ResponseEntity<?> oneuser(@PathVariable Long id) throws Exception {
        return ResponseEntity.ok(userServices.getOneUser(id));
    }
    
    
    @PostMapping("/add")
    public ResponseEntity<?> addUser(@Valid @RequestBody final UserRequest req) throws Exception {
        return ResponseEntity.ok(userServices.addUser(req));
    }
    
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> deleteUser(@PathVariable Long id) throws Exception {
        return ResponseEntity.ok(userServices.deleteUser(id));
    }
    
    @PutMapping("/update/{id}")
    public ResponseEntity<?> updateUser(@PathVariable Long id, @Valid @RequestBody final UserRequest req) throws Exception {
        return ResponseEntity.ok(userServices.updateUser(id,req));
    }
    
}
