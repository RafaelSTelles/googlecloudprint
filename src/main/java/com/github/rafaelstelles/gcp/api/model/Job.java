package com.github.rafaelstelles.gcp.api.model;

import java.util.Set;

import com.google.gson.Gson;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class Job {

    private String createTime;
    private Set<String> tags;
    private String printerName;
    private String updateTime;
    private JobStatus status;
    private String ownerId;
    private String ticketUrl;
    private String printerid;
    private String printerType;
    private String contentType;
    private String fileUrl;
    private String id;
    private String message;
    private String title;
    private String errorCode;
    private int numberOfPages;
    private Gson gson = new Gson();

    public String toJson() {
        return gson.toJson(this);
    }
}
