package com.mycreation.jobsPortal.repositories;

import com.mycreation.jobsPortal.model.Job;
import com.mycreation.jobsPortal.model.JobApplication;
import com.mycreation.jobsPortal.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ApplicationRepository extends JpaRepository <JobApplication,Long> {

    List<JobApplication> findByUserId(Long user_id);
    List<JobApplication> findByJobId(Long job_id);

    //custom query
    /*@Query("SELECT ja FROM JobApplication ja JOIN FETCH ja.user WHERE ja.job.id = :jobId")
    List<JobApplication> findApplicationsByJobId(@Param("jobId") Long jobId);*/

}
