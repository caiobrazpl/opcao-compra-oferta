package com.caiobraz.opcaocompraoferta.core.resource.dto;

import java.util.ArrayList;
import java.util.List;

import org.springframework.http.HttpStatus;

public class ValidationErrorMessage extends ErrorMassage {

    private List<ValidationMessage> errors = new ArrayList<>();

    public ValidationErrorMessage(HttpStatus status, String message, String path) {
        super(status, message, path);
    }

    public ValidationErrorMessage(Integer status, String error, String message, String path) {
        super(status, error, message, path);
    }

    public void addErro(String field, String message) {
        errors.add(new ValidationMessage(field, message));
    }

    public List<ValidationMessage> getErrors() {
        return errors;
    }

    public void setErrors(List<ValidationMessage> errors) {
        this.errors = errors;
    }
}
