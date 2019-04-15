package kz.maksat.common.exception;

public class UnRecoverableException extends Exception {

    public UnRecoverableException(String message) {
        super(message);
    }

    public UnRecoverableException(String message, Throwable cause) {
        super(message, cause);
    }

}
