package com.caiobraz.opcaocompraoferta.core.exception;


import com.caiobraz.opcaocompraoferta.core.resource.dto.ErrorMassage;

public class ServiceException extends RuntimeException {

    private ErrorMassage error;

    public ServiceException(String message) {
        super(message);
    }

    public ServiceException(ErrorMassage error) {
        super(error.getMessage());
        this.error = error;
    }

    public ServiceException(String message, Throwable cause) {
        super(message, cause);
    }

    public ErrorMassage getError() {
        return error;
    }

    public void setError(ErrorMassage error) {
        this.error = error;
    }
}
