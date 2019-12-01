package com.caiobraz.opcaocompraoferta.core.resource.handler;

import java.util.Optional;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import com.caiobraz.opcaocompraoferta.core.exception.ServiceException;
import com.caiobraz.opcaocompraoferta.core.resource.dto.ErrorMassage;
import com.caiobraz.opcaocompraoferta.core.resource.dto.ValidationErrorMessage;
import com.caiobraz.opcaocompraoferta.core.service.MessagesService;

@ControllerAdvice
public class DefaultHandler {

    private MessagesService messages;

    @Autowired
    public DefaultHandler(MessagesService messages) {
        this.messages = messages;
    }

    @ExceptionHandler(value = ServiceException.class)
    public ResponseEntity<ErrorMassage> handleServiceException(ServiceException e, HttpServletRequest request) {
        HttpStatus status = HttpStatus.BAD_REQUEST;
        if (e.getError() != null && e.getError().getStatus() != null) {
            status = Optional.ofNullable(HttpStatus.resolve(e.getError().getStatus())).orElse(HttpStatus.BAD_REQUEST);
        }

        ErrorMassage error = new ErrorMassage(status, e.getMessage(), request.getRequestURI());

        return ResponseEntity.status(error.getStatus()).body(error);
    }

    @ExceptionHandler(value = MethodArgumentNotValidException.class)
    public ResponseEntity<ValidationErrorMessage> handleMethodArgumentNotValidException(MethodArgumentNotValidException e, HttpServletRequest request) {
        ValidationErrorMessage error = new ValidationErrorMessage(
                HttpStatus.UNPROCESSABLE_ENTITY, this.messages.string("default.validationError"), request.getRequestURI()
        );

        for (FieldError fieldError : e.getBindingResult().getFieldErrors()) {
            error.addErro(fieldError.getField(), fieldError.getDefaultMessage());
        }

        return ResponseEntity.status(error.getStatus()).body(error);
    }
}
