package com.mycreation.jobsPortal.rest_controllers;

import com.mycreation.jobsPortal.model.Job;
import com.mycreation.jobsPortal.model.Role;
import com.mycreation.jobsPortal.model.User;
import com.mycreation.jobsPortal.repositories.UserRepository;
import com.mycreation.jobsPortal.services.JobService;
import com.mycreation.jobsPortal.utils.JwtUtil;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController()
@RequestMapping("/api/jobs")
@SecurityRequirement(name = "bearerAuth")
public class JobController {

    @Autowired
    JobService service;

    @Autowired
    JwtUtil jwtUtil;

    @Autowired
    UserRepository userRepository;

    @Operation(summary = "Enables users with Employer role to post new jobs")
    @PostMapping("/post")
    public ResponseEntity<Job> postJob(@RequestBody Job job, @RequestHeader("Authorization") String token){
        String email=jwtUtil.extractEmail(token.replace("Bearer ", ""));
        User user = userRepository.findByEmail(email).orElseThrow();

        //only users with the EMPLOYER role can post jobs.
        if (user.getRole() != Role.EMPLOYER) {
            return ResponseEntity.status(403).body(null); // Forbidden
        }else{
        job.setId(null); // to avoid overwriting
        return ResponseEntity.ok(service.postJob(job));}
    }

    @Operation(summary = "Returns list of the jobs available according to page size")
    @GetMapping
    public ResponseEntity<Page<Job>> getAllJobs(@RequestParam(defaultValue = "0") int page, @RequestParam(defaultValue = "10") int size){
        Pageable pageable= PageRequest.of(page,size);
        return ResponseEntity.ok(service.getAllJobs(pageable));
    }

    @Operation(summary = "Returns a job with give id")
    @GetMapping("/{id}")
    public ResponseEntity<Job> getJobById(@PathVariable Long id){
        if (service.findJobById(id).isPresent()){
            return ResponseEntity.ok(service.findJobById(id).get());
        }else{
            return ResponseEntity.notFound().build();
        }
    }

    @Operation(summary = "Returns the list of jobs that matches given skill set")
    @GetMapping("/search")
    public ResponseEntity<List<Job>> getJobBySkillSet(@RequestParam String skill){
        return ResponseEntity.ok(service.findJobBySkillSet(skill));
    }

}
