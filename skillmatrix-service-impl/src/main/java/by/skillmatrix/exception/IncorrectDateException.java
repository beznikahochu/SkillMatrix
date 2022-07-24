package by.skillmatrix.exception;

public class IncorrectDateException extends RuntimeException {
    public IncorrectDateException() {
    }

    public IncorrectDateException(String message) {
        super(message);
    }
}
