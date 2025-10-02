package com.project.services.exceptions;

// Exceção customizada para quando um objeto não é encontrado no sistema
public class ObjectNotFoundException extends RuntimeException {
    private static final long serialVersionUID = 1L;

    // Construtor com mensagem e causa
    public ObjectNotFoundException(String message, Throwable cause) {
        super(message, cause);
    }

    // Construtor com apenas mensagem
    public ObjectNotFoundException(String message) {
        super(message);
    }
}