package br.com.rafaelstelles.gcp.api.model.response;

import java.util.List;

import br.com.rafaelstelles.gcp.api.model.Printer;

public class RegisterPrinterResponse extends CloudPrintResponse {

    private List<Printer> printers;

    public List<Printer> getPrinters() {
        return printers;
    }

    public void setPrinters(List<Printer> printers) {
        this.printers = printers;
    }
}
