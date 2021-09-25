package ru.lanit.lanit_task.web;

import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpStatus;
import org.springframework.validation.BindException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import ru.lanit.lanit_task.util.exception.ExistException;
import ru.lanit.lanit_task.util.exception.NotFoundException;


@RestControllerAdvice(annotations = RestController.class)
@Order(Ordered.HIGHEST_PRECEDENCE + 5)
public class ExceptionInfoHandler {

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(BindException.class)
    public void bindValidationError(BindException e) {

    }

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(ExistException.class)
    public void idExistError(ExistException e) {

    }

    @ResponseStatus(HttpStatus.NOT_FOUND)
    @ExceptionHandler(NotFoundException.class)
    public void notFoundError(NotFoundException e) {

    }

/*
    @ExceptionHandler(BindException.class)
    public ResponseEntity<String> bindValidationError(BindException e) {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(
                e.getBindingResult().getFieldErrors().stream()
                        .map(fe -> String.format("[%s] %s", fe.getField(), fe.getDefaultMessage()))
                        .collect(Collectors.joining("\n")));
    }

    @ExceptionHandler(ExistException.class)
    public ResponseEntity<String> idExistError(ExistException e) {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
    }

    @ExceptionHandler(NotFoundException.class)
    public ResponseEntity<String> notFoundError(NotFoundException e) {
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
    }*/
}
