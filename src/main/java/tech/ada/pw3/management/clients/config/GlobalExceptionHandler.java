package tech.ada.pw3.management.clients.config;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import tech.ada.pw3.management.clients.exception.DataNotFoundException;
import tech.ada.pw3.management.clients.exception.StandardError;

import java.util.Objects;

@ControllerAdvice
public class GlobalExceptionHandler {
    @ResponseBody
    @ExceptionHandler(MethodArgumentNotValidException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public StandardError badRequestHandler(MethodArgumentNotValidException e) {
        StandardError standardError = new StandardError();
        standardError.setMessage(Objects.requireNonNull(e.getFieldError()).getDefaultMessage());
        standardError.setStatus(HttpStatus.BAD_REQUEST.value());
        standardError.setTimestamp(System.currentTimeMillis());
        return standardError;
    }

    @ResponseBody
    @ExceptionHandler({DataNotFoundException.class})
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public StandardError notFoundHandler(Exception e) {
        StandardError standardError = new StandardError();
        standardError.setMessage(e.getMessage());
        standardError.setStatus(HttpStatus.NOT_FOUND.value());
        standardError.setTimestamp(System.currentTimeMillis());
        return standardError;
    }
}
