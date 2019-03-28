package com.github.rafaelstelles.gcp.api.model;


public class SubmitJobResponse extends CloudPrintResponse {

    private Job job;

    public Job getJob() {
        return job;
    }

    public void setJob(Job job) {
        this.job = job;
    }
}
