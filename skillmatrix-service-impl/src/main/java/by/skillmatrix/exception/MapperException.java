package by.skillmatrix.exception;

/**
 * Exception for mapping actions.
 */
public class MapperException extends RuntimeException {

    public MapperException() {
        super();
    }

    /**
     * This constructor need for MapStruct.
     *
     * @param message about mapping problem
     */
    public MapperException(String message) {
        super(message);
    }
}
