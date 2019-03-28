package com.github.rafaelstelles.gcp.api.model;

import java.util.List;

import com.google.gson.Gson;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class SubmitJob {

	private String printerId;
    private String title;
    private Ticket ticket;
    private byte[] content;
    private String contentType;
    private List<String> tag;
    private Gson gson = new Gson();

	public String toJson(){
		return gson.toJson(ticket);
	}

}
