package br.com.rafaelstelles.gcp.api.exception;

public class CloudPrintException extends Exception {

    public CloudPrintException() {
        super();
    }

    public CloudPrintException(String message) {
        super(message);
    }

    public CloudPrintException(String message, Throwable cause) {
        super(message, cause);
    }

    public CloudPrintException(Throwable cause) {
        super(cause);
    }

    protected CloudPrintException(String message, Throwable cause,
            boolean enableSuppression,
            boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
