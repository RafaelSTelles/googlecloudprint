package br.com.rafaelstelles.gcp.api.model.response;

import br.com.rafaelstelles.gcp.api.model.Job;


public class ControlJobResponse extends CloudPrintResponse{

    private Job job;

    public Job getJob() {
        return job;
    }

    public void setJob(Job job) {
        this.job = job;
    }
}
