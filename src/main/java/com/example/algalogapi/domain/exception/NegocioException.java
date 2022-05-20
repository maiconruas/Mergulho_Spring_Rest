package com.example.algalogapi.domain.exception;

public class NegocioException extends RuntimeException {

    public NegocioException(String message) {
        super(message);
    }
}
