package br.com.rafaelstelles.gcp.api.model;

public class TypedValueCapability {

    private String value_type;
    private String deflt;

    public String getValue_type() {
        return value_type;
    }

    public void setValue_type(String value_type) {
        this.value_type = value_type;
    }

    public String getDefault() {
        return deflt;
    }

    public void setDefault(String deflt) {
        this.deflt = deflt;
    }
}
