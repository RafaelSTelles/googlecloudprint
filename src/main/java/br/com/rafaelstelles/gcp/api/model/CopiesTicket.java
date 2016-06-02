package br.com.rafaelstelles.gcp.api.model;

public class CopiesTicket {

    private int copies;

	protected CopiesTicket() {
	}

	public CopiesTicket(int copies) {
		this.copies = copies;
	}

	public int getCopies() {
        return copies;
    }

    public void setCopies(int copies) {
        this.copies = copies;
    }
}
