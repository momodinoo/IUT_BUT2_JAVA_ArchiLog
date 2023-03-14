package app.client.exceptions;

public class NonExistentPortException extends RuntimeException{

    public static final int EXIT_CODE = -5;

    public NonExistentPortException() {
        super();
    }

    public NonExistentPortException(String errorMessage) {
        super(errorMessage);
    }

    public NonExistentPortException(String errorMessage, Throwable error) {
        super(errorMessage, error);
    }

}
