package br.com.rafaelstelles.gcp.api.model;

import java.util.List;

public class Duplex {

    private List<DuplexOption> option;

    public List<DuplexOption> getOption() {
        return option;
    }

    public void setOption(List<DuplexOption> option) {
        this.option = option;
    }
}
