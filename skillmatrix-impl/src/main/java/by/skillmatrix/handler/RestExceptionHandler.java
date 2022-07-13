package by.skillmatrix.handler;

import by.skillmatrix.error.ErrorDto;
import by.skillmatrix.exception.NotFoundException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

/**
 * Exception Handler Controller.
 */
@Slf4j
@RestControllerAdvice
@RequiredArgsConstructor
public class RestExceptionHandler {

    @ResponseStatus(HttpStatus.NOT_FOUND)
    @ExceptionHandler(NotFoundException.class)
    protected ErrorDto handleNotFoundException(NotFoundException exception) {
        log.info("Caught not found exception: {}", exception.toString());

        ErrorDto notFoundError = new ErrorDto();
        notFoundError.setMessage(exception.getMessage());
        notFoundError.setErrorCode(HttpStatus.NOT_FOUND.value());

        log.info("Handled not found error, message: {}, error code: {}",
                notFoundError.getMessage(),
                notFoundError.getErrorCode());

        return notFoundError;
    }

    @ResponseStatus(HttpStatus.FORBIDDEN)
    @ExceptionHandler(AccessDeniedException.class)
    protected ErrorDto handleAccessDeniedException(AccessDeniedException exception) {
        log.info("Caught not found exception: {}", exception.toString());

        ErrorDto errorDto = new ErrorDto();
        errorDto.setMessage(exception.getMessage());
        errorDto.setErrorCode(HttpStatus.FORBIDDEN.value());

        log.info("Handled not found error, message: {}, error code: {}",
                errorDto.getMessage(),
                errorDto.getErrorCode());

        return errorDto;
    }

    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    @ExceptionHandler(RuntimeException.class)
    protected String handleRuntimeException(RuntimeException exception) {
        return "Internal Server Error. \nDon't worry. We will fix it in 5 minutes =)";
    }
}
