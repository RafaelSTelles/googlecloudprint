package br.com.rafaelstelles.gcp.api.model;

/**
 * connectionStatus: printer's connection status, which can be one of the
 * following:<br/><br/>
 * <b>ONLINE</b>: The printer has an active XMPP connection to Google Cloud
 * Print.<br/>
 * <b>UNKNOWN</b>: The printer's connection status cannot be determined.<br/>
 * <b>OFFLINE</b>: The printer is offline.<br/>
 * <b>DORMANT</b>: The printer has been offline for quite a while.<br/>
 * <b>ALL</b> : Match all printers.
 *
 * @author jittagorn pitakmetagoon
 */
public enum PrinterStatus {

    /**
     * <b>ONLINE</b>: The printer has an active XMPP connection to Google Cloud
     * Print.
     */
    ONLINE("Online", "green"),
    /**
     * <b>UNKNOWN</b>: The printer's connection status cannot be determined.
     */
    UNKNOWN("Desconhecido", "yellow"),
    /**
     * <b>OFFLINE</b>: The printer is offline.
     */
    OFFLINE("Offline", "red"),
    /**
     * <b>DORMANT</b>: The printer has been offline for quite a while.
     */
    DORMANT("Online por muito tempo", "orange"),
    /**
     * <b>ALL</b> : Match all printers.
     */
    ALL("Todos", "blue");

	private final String descricao;
	private final String color;

	PrinterStatus(String descricao, String color) {
		this.descricao = descricao;
		this.color = color;
	}

	public String getDescricao() {
		return descricao;
	}

	public String getColor() {
		return color;
	}
}