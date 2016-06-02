package br.com.rafaelstelles.gcp.api.model;

public class Ticket {

    private String version = "1.0";
    private PrintTicket print;

    public String getVersion() {
        return version;
    }

    public void setVersion(String version) {
        this.version = version;
    }

    public PrintTicket getPrint() {
        return print;
    }

    public void setPrint(PrintTicket print) {
        this.print = print;
    }
}
