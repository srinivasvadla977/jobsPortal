package com.mycreation.jobsPortal.repositories;

import com.mycreation.jobsPortal.model.Job;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface JobRepository extends JpaRepository<Job,Long> {

    List<Job> findBySkillSetContaining(String skillSet);

}
