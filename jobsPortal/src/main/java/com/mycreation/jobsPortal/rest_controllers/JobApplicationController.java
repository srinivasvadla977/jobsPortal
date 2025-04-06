package com.mycreation.jobsPortal.rest_controllers;

import com.mycreation.jobsPortal.dto.JobApplicationRequest;
import com.mycreation.jobsPortal.model.ApplicationStatus;
import com.mycreation.jobsPortal.model.JobApplication;
import com.mycreation.jobsPortal.model.Role;
import com.mycreation.jobsPortal.model.User;
import com.mycreation.jobsPortal.repositories.UserRepository;
import com.mycreation.jobsPortal.services.JobApplicationService;
import com.mycreation.jobsPortal.utils.JwtUtil;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@SecurityRequirement(name = "bearerAuth")
@RequestMapping("/api/applications")
public class JobApplicationController {

    @Autowired
    JobApplicationService jobApplicationService;

    @Autowired
    JwtUtil jwtUtil;

    @Autowired
    UserRepository userRepository;

    @Operation(summary = "Returns all the applications applied by the current user")
    @GetMapping("/user")
    public ResponseEntity<Page<JobApplication>> getUserApplications(@RequestParam(defaultValue = "0") int page,
                                                    @RequestParam(defaultValue = "10") int size,@RequestHeader("Authorization") String token ){
        String email=jwtUtil.extractEmail(token.replace("Bearer ", ""));
        User user = userRepository.findByEmail(email).orElseThrow();

        Pageable pageable = PageRequest.of(page, size);
        return ResponseEntity.ok(jobApplicationService.getApplicationsByUser(user.getId(),pageable));
    }

    @Operation(summary = "Returns all the job applications for a given job")
    @GetMapping("/job/{id}")
    public ResponseEntity<Page<JobApplication>> getJobApplications(@PathVariable("id") Long job_id, @RequestParam(defaultValue = "0") int page,
                                                                   @RequestParam(defaultValue = "10") int size){
        Pageable pageable = PageRequest.of(page, size);
        return ResponseEntity.ok(jobApplicationService.getApplicationsByJob(job_id,pageable));
    }

    //using request body of DTO
    @Operation(summary = "Enables users to apply for a job", description = "Requires userId, jobId, and a optional cover letter")
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

    @Operation(summary = "Enables users with Employer role to update a application status")
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
