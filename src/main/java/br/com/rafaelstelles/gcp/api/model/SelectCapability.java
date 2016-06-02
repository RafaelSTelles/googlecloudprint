package br.com.rafaelstelles.gcp.api.model;

import java.util.List;

public class SelectCapability {

    private List<SelectCapabilityOption> option;

    public List<SelectCapabilityOption> getOption() {
        return option;
    }

    public void setOption(List<SelectCapabilityOption> option) {
        this.option = option;
    }
}
