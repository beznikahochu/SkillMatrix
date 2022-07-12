package by.skillmatrix.exception;

public class AuthenticationException extends RuntimeException{

    public static final String UNAUTHORIZED = "Incorrect login or password";

    public AuthenticationException(String message) {
        super(message);
    }
}
