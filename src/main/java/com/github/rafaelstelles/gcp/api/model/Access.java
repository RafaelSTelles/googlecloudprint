package com.github.rafaelstelles.gcp.api.model;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class Access {

    private String membership;
    private String email;
    private String name;
    private String role;
    private String USER;
    private boolean is_pending;

}
