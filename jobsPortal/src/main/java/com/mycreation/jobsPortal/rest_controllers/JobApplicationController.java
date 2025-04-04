package com.mycreation.jobsPortal.rest_controllers;

import com.mycreation.jobsPortal.dto.JobApplicationRequest;
import com.mycreation.jobsPortal.model.ApplicationStatus;
import com.mycreation.jobsPortal.model.JobApplication;
import com.mycreation.jobsPortal.model.Role;
import com.mycreation.jobsPortal.model.User;
import com.mycreation.jobsPortal.repositories.UserRepository;
import com.mycreation.jobsPortal.services.JobApplicationService;
import com.mycreation.jobsPortal.utils.JwtUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/applications")
public class JobApplicationController {

    @Autowired
    JobApplicationService jobApplicationService;

    @Autowired
    JwtUtil jwtUtil;

    @Autowired
    UserRepository userRepository;


    @GetMapping("/user/{id}")
    public List<JobApplication> getUserApplications(@PathVariable("id") Long user_id){
        return jobApplicationService.getApplicationsByUser(user_id);
    }

    @GetMapping("/job/{id}")
    public List<JobApplication> getJobApplications(@PathVariable("id") Long job_id){
        return jobApplicationService.getApplicationsByJob(job_id);
    }

    //using request body of DTO
    @PostMapping("/apply")
    public ResponseEntity<JobApplication> applyForJob(@RequestBody JobApplicationRequest application, @RequestHeader("Authorization") String token){

        String email=jwtUtil.extractEmail(token.replace("Bearer ", ""));
        User user = userRepository.findByEmail(email).orElseThrow();

        //only users with the USER role can apply a job.
        if (user.getRole() != Role.USER) {
            return ResponseEntity.status(403).body(null); // Forbidden
        }else{
            return ResponseEntity.ok(jobApplicationService.applyForJob(user.getId(), application.getJobId(), application.getCoverLetter())); }
    }

    //using request params
    /*@PostMapping("/apply")
    public ResponseEntity<JobApplication> applyForJob(@RequestParam Long userId, @RequestParam Long jobId, @RequestParam String coverLetter){
        return ResponseEntity.ok(jobApplicationService.applyForJob(userId, jobId, coverLetter));
    }*/

    @PutMapping("/{apkId}/status")
    public ResponseEntity<JobApplication> updateApplicationStatus(@PathVariable Long apkId, @RequestParam ApplicationStatus status, @RequestHeader("Authorization") String token){

        String email=jwtUtil.extractEmail(token.replace("Bearer ", ""));
        User user = userRepository.findByEmail(email).orElseThrow();

        //only users with the EMPLOYER role can update a job status.
        if (user.getRole() != Role.EMPLOYER) {
            return ResponseEntity.status(403).body(null); // Forbidden
        }else{
        return ResponseEntity.ok(jobApplicationService.updateApplicationStatus(apkId,status)); }
    }

}
