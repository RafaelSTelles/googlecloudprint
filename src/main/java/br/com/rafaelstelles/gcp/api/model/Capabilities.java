package br.com.rafaelstelles.gcp.api.model;

public class Capabilities {

    private PrinterCapability printer;
    private String version;

    public PrinterCapability getPrinter() {
        return printer;
    }

    public void setPrinter(PrinterCapability printer) {
        this.printer = printer;
    }

    public String getVersion() {
        return version;
    }

    public void setVersion(String version) {
        this.version = version;
    }
}
