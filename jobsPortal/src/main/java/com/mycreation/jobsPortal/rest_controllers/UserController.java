package com.mycreation.jobsPortal.rest_controllers;


import com.mycreation.jobsPortal.model.User;
import com.mycreation.jobsPortal.services.UserService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/users")
public class UserController {

    @Autowired
    UserService service;

    @Operation(summary = "Public end point for new users to register")
    @PostMapping("/register")
    public ResponseEntity<User> registerUser(@RequestBody() User user){
       user.setId(null); // to avoid overwriting existing users
       return ResponseEntity.ok(service.registerUser(user));
    }

    @Operation(summary = "Returns a user matching with the give email id")
    @SecurityRequirement(name = "bearerAuth")
    @GetMapping("/{email}")
    public ResponseEntity<User> getUserByMail(@PathVariable("email") String mail){
        if (service.getUserByEmail(mail).isPresent()){
          return ResponseEntity.ok(service.getUserByEmail(mail).get());
        }else {
          return ResponseEntity.notFound().build();
        }
    }

}
