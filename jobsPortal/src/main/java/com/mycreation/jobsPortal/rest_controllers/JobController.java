package com.mycreation.jobsPortal.rest_controllers;

import com.mycreation.jobsPortal.model.Job;
import com.mycreation.jobsPortal.model.Role;
import com.mycreation.jobsPortal.model.User;
import com.mycreation.jobsPortal.repositories.UserRepository;
import com.mycreation.jobsPortal.services.JobService;
import com.mycreation.jobsPortal.utils.JwtUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController()
@RequestMapping("/api/jobs")
public class JobController {

    @Autowired
    JobService service;

    @Autowired
    JwtUtil jwtUtil;

    @Autowired
    UserRepository userRepository;

    @PostMapping("/post")
    public ResponseEntity<Job> postJob(@RequestBody Job job, @RequestHeader("Authorization") String token){
        String email=jwtUtil.extractEmail(token.replace("Bearer ", ""));
        User user = userRepository.findByEmail(email).orElseThrow();

        //only users with the EMPLOYER role can post jobs.
        if (user.getRole() != Role.EMPLOYER) {
            return ResponseEntity.status(403).body(null); // Forbidden
        }else{
        return ResponseEntity.ok(service.postJob(job));}
    }

    @GetMapping
    public ResponseEntity<List<Job>> getAllJobs(){
        return ResponseEntity.ok(service.getAllJobs());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Job> getJobById(@PathVariable Long id){
        if (service.findJobById(id).isPresent()){
            return ResponseEntity.ok(service.findJobById(id).get());
        }else{
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping("/search")
    public ResponseEntity<List<Job>> getJobBySkillSet(@RequestParam String skill){
        return ResponseEntity.ok(service.findJobBySkillSet(skill));
    }

}
