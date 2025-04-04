package com.mycreation.jobsPortal.dto;

public class JobApplicationRequest {
   // private Long userId;
    private Long jobId;
    private String coverLetter;

    public JobApplicationRequest() {
    }

   /* public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }*/

    public Long getJobId() {
        return jobId;
    }

    public void setJobId(Long jobId) {
        this.jobId = jobId;
    }

    public String getCoverLetter() {
        return coverLetter;
    }

    public void setCoverLetter(String coverLetter) {
        this.coverLetter = coverLetter;
    }
}
