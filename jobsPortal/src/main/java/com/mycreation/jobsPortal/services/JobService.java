package com.mycreation.jobsPortal.services;

import com.mycreation.jobsPortal.model.Job;
import com.mycreation.jobsPortal.repositories.JobRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

import org.springframework.data.domain.Pageable;
import java.util.List;
import java.util.Optional;

@Service
public class JobService {

    @Autowired
    private JobRepository jobRepository;

    public Job postJob(Job job){
       return jobRepository.save(job);
    }

    public Page<Job> getAllJobs(Pageable pageable){
        return jobRepository.findAll(pageable);
    }

    public Optional<Job> findJobById(Long id){
        return  jobRepository.findById(id);
    }

    public List<Job> findJobBySkillSet(String skillSet){
        return jobRepository.findBySkillSetContaining(skillSet);
    }


}
