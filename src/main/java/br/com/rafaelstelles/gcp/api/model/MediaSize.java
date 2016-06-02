package br.com.rafaelstelles.gcp.api.model;

import java.util.List;

public class MediaSize {

    private List<MediaSizeOption> option;

    public List<MediaSizeOption> getOption() {
        return option;
    }

    public void setOption(List<MediaSizeOption> option) {
        this.option = option;
    }
}
