package com.project.resources.exceptions;

import java.util.ArrayList;
import java.util.List;

// Classe especializada para erros de validação com múltiplos campos
public class ValidationError extends StandardError {

    private List<FieldMessage> errors = new ArrayList<>(); // Lista de erros por campo

    public ValidationError() {
        super();
    }

    public ValidationError(long timeStamp, Integer status, String error, String message, String path) {
        super(timeStamp, status, error, message, path);
    }

    // GETTERS E SETTERS
    public List<FieldMessage> getErrors() { return errors; }

    // Método para adicionar erro de campo específico
    public void addError(String fieldName, String message) {
        this.errors.add(new FieldMessage(fieldName, message));
    }
}