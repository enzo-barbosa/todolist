package com.project.domains.enums;

// Enum que define os níveis de prioridade de uma tarefa
public enum Prioridade {
    BAIXA,
    MEDIA,
    ALTA;

    // Método auxiliar para conversão segura de string para enum
    public static Prioridade fromString(String value) {
        if (value == null) return null;
        try {
            return Prioridade.valueOf(value.toUpperCase()); // Converte para maiúsculas
        } catch (IllegalArgumentException e) {
            throw new IllegalArgumentException("Prioridade inválida: " + value +
                    ". Valores aceitos: " + java.util.Arrays.toString(Prioridade.values()));
        }
    }
}