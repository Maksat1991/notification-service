package kz.maksat.common.exception;

public class ParseMessageException extends UnRecoverableException {

    private final String data;
    private final Class clazz;

    public ParseMessageException(String message, String data, Class clazz) {
        super(message);
        this.data = data;
        this.clazz = clazz;
    }

    public ParseMessageException(String message, Throwable cause,  String data, Class clazz) {
        super(message, cause);
        this.data = data;
        this.clazz = clazz;
    }

    @Override
    public String toString() {
        return super.toString() + " ParseMessageException{" +
                "data='" + data + '\'' +
                ", clazz=" + clazz +
                '}';
    }
}

