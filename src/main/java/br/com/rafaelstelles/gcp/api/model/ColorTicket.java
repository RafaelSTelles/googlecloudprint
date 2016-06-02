package br.com.rafaelstelles.gcp.api.model;

public class ColorTicket {

    private String vendor_id;
    private int type;

    public String getVendor_id() {
        return vendor_id;
    }

    public void setVendor_id(String vendor_id) {
        this.vendor_id = vendor_id;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }
}
