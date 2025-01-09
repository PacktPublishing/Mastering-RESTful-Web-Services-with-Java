package com.packt.productapi.adapter.exception;

public class ProductTimeoutException extends RuntimeException {
        public ProductTimeoutException(String message, Throwable cause) {
            super(message, cause);
        }
}
