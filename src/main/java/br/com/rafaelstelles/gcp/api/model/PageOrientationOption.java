package br.com.rafaelstelles.gcp.api.model;

public class PageOrientationOption {

    private boolean is_default;
    private String type;

    public boolean isIs_default() {
        return is_default;
    }

    public void setIs_default(boolean is_default) {
        this.is_default = is_default;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }
}
