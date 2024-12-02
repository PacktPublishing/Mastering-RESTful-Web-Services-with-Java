package com.packt.productapi.adapter.exception;

public class UnexpectedServerError extends RuntimeException {

    public UnexpectedServerError(String message, Throwable cause) {
        super(message, cause);
    }
}
