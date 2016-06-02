package br.com.rafaelstelles.gcp.api.model.response;

import java.util.List;

import br.com.rafaelstelles.gcp.api.model.Job;

public class FecthJobResponse extends CloudPrintResponse{

    private List<Job> jobs;
    private String errorCode;


    public List<Job> getJobs() {
        return jobs;
    }

    public void setJobs(List<Job> jobs) {
        this.jobs = jobs;
    }

    public String getErrorCode() {
        return errorCode;
    }

    public void setErrorCode(String errorCode) {
        this.errorCode = errorCode;
    }
}
