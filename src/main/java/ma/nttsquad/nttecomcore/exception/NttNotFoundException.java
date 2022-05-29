package ma.nttsquad.nttecomcore.exception;

public class NttNotFoundException extends RuntimeException{

    public NttNotFoundException(String message) {
        super(message);
    }

    public NttNotFoundException(String message, Throwable cause) {
        super(message, cause);
    }
}
