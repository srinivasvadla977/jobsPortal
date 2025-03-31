package com.mycreation.jobsPortal.services;

import com.mycreation.jobsPortal.model.Job;
import com.mycreation.jobsPortal.repositories.JobRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class JobService {

    @Autowired
    private JobRepository jobRepository;

    public Job postJob(Job job){
       return jobRepository.save(job);
    }

    public List<Job> getAllJobs(){
        return jobRepository.findAll();
    }

    public Optional<Job> findJobById(Long id){
        return  jobRepository.findById(id);
    }

    public List<Job> findJobBySkillSet(String skillSet){
        return jobRepository.findBySkillSetContaining(skillSet);
    }


}
