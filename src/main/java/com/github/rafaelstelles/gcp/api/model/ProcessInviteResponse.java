package com.github.rafaelstelles.gcp.api.model;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class ProcessInviteResponse  extends CloudPrintResponse {
	private Printer printer;
}
