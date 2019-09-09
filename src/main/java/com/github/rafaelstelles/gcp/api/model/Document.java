package com.github.rafaelstelles.gcp.api.model;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor(staticName = "of")
public class Document {

	private final String fileName;
	private final String contentType;
	private final byte[] file;
	private final int copies;

}
