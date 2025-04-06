package com.mycreation.jobsPortal.services;

import com.mycreation.jobsPortal.model.ApplicationStatus;
import com.mycreation.jobsPortal.model.Job;
import com.mycreation.jobsPortal.model.JobApplication;
import com.mycreation.jobsPortal.model.User;
import com.mycreation.jobsPortal.repositories.ApplicationRepository;
import com.mycreation.jobsPortal.repositories.JobRepository;
import com.mycreation.jobsPortal.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

import org.springframework.data.domain.Pageable;

@Service
public class JobApplicationService {

    @Autowired
    ApplicationRepository applicationrepository;

    @Autowired
    JobRepository jobRepository;

    @Autowired
    UserRepository userRepository;

    public JobApplication applyForJob(Long user_id, Long job_id, String coverLetter){

        User user=userRepository.findById(user_id).orElseThrow(()->new RuntimeException("User not found, please signup first!"));
        Job job=jobRepository.findById(job_id).orElseThrow(()->new RuntimeException("Invalid job id"));

       return applicationrepository.save(new JobApplication(user,job,coverLetter, ApplicationStatus.PENDING));
    }

    public Page<JobApplication> getApplicationsByUser(Long user_id, Pageable pageable){
        return applicationrepository.findByUserId(user_id, pageable);
    }

    public Page<JobApplication> getApplicationsByJob(Long job_id, Pageable pageable){
        return applicationrepository.findByJobId(job_id, pageable);
    }

    public JobApplication updateApplicationStatus(Long applicationId, ApplicationStatus status){
        JobApplication application=applicationrepository.findById(applicationId).orElseThrow(()->new RuntimeException("Application not found"));
        application.setStatus(status);

        return applicationrepository.save(application);
    }


}
