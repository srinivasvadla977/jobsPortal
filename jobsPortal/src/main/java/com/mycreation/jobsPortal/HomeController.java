package com.mycreation.jobsPortal;

import com.mycreation.jobsPortal.model.Job;
import com.mycreation.jobsPortal.model.User;
import com.mycreation.jobsPortal.repositories.JobRepository;
import com.mycreation.jobsPortal.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

//@Controller("/")
public class HomeController {

   /* @Autowired
    UserService userService;

    @Autowired
    JobRepository jobRepository;

    @GetMapping("/")
    @ResponseBody
    public List<Job> greet(){
        return jobRepository.findAll();
    }

    @PostMapping("/add")
    @ResponseBody
    public User addUser(){
       // return userService.registerUser(new User(12345L,"srinivas","srini@123.com", "1234", "SEEK"));
        return null;
    }*/
}
