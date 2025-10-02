package com.project.resources.exceptions;

// Classe para representar mensagens de erro de validação de campo
public class FieldMessage {

    private String fieldName; // Nome do campo com erro
    private String message;   // Mensagem de erro

    public FieldMessage() {
        super();
    }

    public FieldMessage(String fieldName, String message) {
        this.fieldName = fieldName;
        this.message = message;
    }

    // GETTERS E SETTERS
    public String getFieldName() { return fieldName; }
    public void setFieldName(String fieldName) { this.fieldName = fieldName; }

    public String getMessage() { return message; }
    public void setMessage(String message) { this.message = message; }
}