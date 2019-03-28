package com.github.rafaelstelles.gcp.api.model;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class PrintTicket {

	private CopiesTicket copies = new CopiesTicket(1);

}
