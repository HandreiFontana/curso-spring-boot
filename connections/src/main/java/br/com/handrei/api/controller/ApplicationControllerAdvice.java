package br.com.handrei.api.controller;

import br.com.handrei.api.ApiErrors;
import br.com.handrei.exception.OrderNotFoundException;
import br.com.handrei.exception.UseCaseException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class ApplicationControllerAdvice {

    @ExceptionHandler(UseCaseException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ApiErrors handleUseCaseException(UseCaseException exception) {
        String message = exception.getMessage();
        return new ApiErrors(message);
    }

    @ExceptionHandler(OrderNotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public ApiErrors handleOrderNotFoundException(OrderNotFoundException exception) {
        return new ApiErrors(exception.getMessage());
    }
}
