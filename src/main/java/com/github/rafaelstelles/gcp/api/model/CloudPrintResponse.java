package com.github.rafaelstelles.gcp.api.model;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class CloudPrintResponse {

    private boolean success;
    private String message;
    private String xsrf_token;

}
