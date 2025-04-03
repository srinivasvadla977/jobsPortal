package com.mycreation.jobsPortal.rest_controllers;

import com.mycreation.jobsPortal.dto.JobApplicationRequest;
import com.mycreation.jobsPortal.model.ApplicationStatus;
import com.mycreation.jobsPortal.model.JobApplication;
import com.mycreation.jobsPortal.services.JobApplicationService;
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
    public ResponseEntity<JobApplication> applyForJob(@RequestBody JobApplicationRequest application){
       return ResponseEntity.ok(jobApplicationService.applyForJob(application.getUserId(), application.getJobId(), application.getCoverLetter()));
    }

    //using request params
    /*@PostMapping("/apply")
    public ResponseEntity<JobApplication> applyForJob(@RequestParam Long userId, @RequestParam Long jobId, @RequestParam String coverLetter){
        return ResponseEntity.ok(jobApplicationService.applyForJob(userId, jobId, coverLetter));
    }*/

    @PutMapping("/{apkId}/status")
    public ResponseEntity<JobApplication> updateApplicationStatus(@PathVariable Long apkId, @RequestParam ApplicationStatus status){
        return ResponseEntity.ok(jobApplicationService.updateApplicationStatus(apkId,status));
    }

}
