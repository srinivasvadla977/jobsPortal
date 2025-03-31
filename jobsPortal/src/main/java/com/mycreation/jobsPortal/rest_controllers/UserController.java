package com.mycreation.jobsPortal.rest_controllers;


import com.mycreation.jobsPortal.model.User;
import com.mycreation.jobsPortal.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/users")
public class UserController {

    @Autowired
    UserService service;

    @PostMapping("/register")
    public ResponseEntity<User> registerUser(@RequestBody() User user){
       return ResponseEntity.ok(service.registerUser(user));
    }

    @GetMapping("/{email}")
    public ResponseEntity<User> getUserByMail(@PathVariable("email") String mail){
        if (service.getUserByEmail(mail).isPresent()){
          return ResponseEntity.ok(service.getUserByEmail(mail).get());
        }else {
          return ResponseEntity.notFound().build();
        }
    }

}
