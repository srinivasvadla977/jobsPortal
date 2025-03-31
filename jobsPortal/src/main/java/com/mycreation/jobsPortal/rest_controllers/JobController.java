package com.mycreation.jobsPortal.rest_controllers;

import com.mycreation.jobsPortal.model.Job;
import com.mycreation.jobsPortal.services.JobService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController()
@RequestMapping("/api/jobs")
public class JobController {

    @Autowired
    JobService service;

    @PostMapping("/post")
    public ResponseEntity<Job> postJob(@RequestBody Job job){
        return ResponseEntity.ok(service.postJob(job));
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
