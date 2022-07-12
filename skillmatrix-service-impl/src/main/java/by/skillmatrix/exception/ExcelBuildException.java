package by.skillmatrix.exception;

public class ExcelBuildException extends RuntimeException {
    public ExcelBuildException() {
        super();
    }

    public ExcelBuildException(String message) {
        super(message);
    }

    public ExcelBuildException(Throwable cause) {
        super(cause);
    }
}
