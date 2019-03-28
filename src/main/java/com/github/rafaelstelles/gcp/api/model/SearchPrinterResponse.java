package com.github.rafaelstelles.gcp.api.model;

import java.util.List;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class SearchPrinterResponse extends CloudPrintResponse {

	private List<Printer> printers;

}
