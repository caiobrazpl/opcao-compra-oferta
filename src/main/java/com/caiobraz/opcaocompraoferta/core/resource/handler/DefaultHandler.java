package com.caiobraz.opcaocompraoferta.core.resource.handler;

import java.util.Optional;

import javax.servlet.http.HttpServletRequest;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import com.caiobraz.opcaocompraoferta.core.exception.ServiceException;
import com.caiobraz.opcaocompraoferta.core.resource.dto.ErrorMassage;

@ControllerAdvice
public class DefaultHandler {

    @ExceptionHandler(value = ServiceException.class)
    public ResponseEntity<ErrorMassage> handleServiceException(ServiceException e, HttpServletRequest request) {
        HttpStatus status = HttpStatus.BAD_REQUEST;
        if (e.getError() != null && e.getError().getStatus() != null) {
            status = Optional.ofNullable(HttpStatus.resolve(e.getError().getStatus())).orElse(HttpStatus.BAD_REQUEST);
        }

        ErrorMassage error = new ErrorMassage(status, e.getMessage(), request.getRequestURI());

        return ResponseEntity.status(error.getStatus()).body(error);
    }
}
