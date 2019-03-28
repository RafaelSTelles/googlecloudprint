package com.github.rafaelstelles.gcp.api.model;

import java.util.List;

public class SearchPrinterResponse extends CloudPrintResponse{

    private List<Printer> printers;

    public List<Printer> getPrinters() {
        return printers;
    }

    public void setPrinters(List<Printer>  printers) {
        this.printers = printers;
    }

	@Override
	public String toString() {
		return "SearchPrinterResponse{" +
				"printers=" + printers +
				"} " + super.toString();
	}
}
