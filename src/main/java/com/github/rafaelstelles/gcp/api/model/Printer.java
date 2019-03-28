package com.github.rafaelstelles.gcp.api.model;

import java.util.List;
import java.util.Set;

import com.google.gson.Gson;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@EqualsAndHashCode(of = "id")
public class Printer {

    private String id;
    private String ownerId;
    private String name;
    private String proxy;
    private String description;
    private String status;
    private PrinterStatus connectionStatus;
    private String supportedContentTypes;
    private String createTime;
    private String updateTime;
    private String accessTime;
    private String type;
    private String gcpVersion;
    private String capsHash;
    private boolean isTosAccepted;
    private String defaultDisplayName;
    private String displayName;
    private Set<String> tags;
    private Object capabilities;
    private Object defaults;
    private List<Access> access;
    private String capsFormat;

    private Gson gson = new Gson();

    public String toJson() {
        return gson.toJson(this);
    }
}
