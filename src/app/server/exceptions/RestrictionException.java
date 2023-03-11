package app.server.exceptions;

public class RestrictionException extends RuntimeException{

    public RestrictionException(String errorMessage) {
        super(errorMessage);
    }

    public RestrictionException(String errorMessage, Throwable error) {
        super(errorMessage, error);
    }

}
