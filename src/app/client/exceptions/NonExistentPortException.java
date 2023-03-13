package app.client.exceptions;

public class NonExistentPortException extends RuntimeException{

    NonExistentPortException() {
        super();
    }

    public NonExistentPortException(String errorMessage) {
        super(errorMessage);
    }

    public NonExistentPortException(String errorMessage, Throwable error) {
        super(errorMessage, error);
    }

}
