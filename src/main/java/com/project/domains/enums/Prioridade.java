package com.project.domains.enums;

public enum Prioridade {
    BAIXA,
    MEDIA,
    ALTA;

    // Método auxiliar para conversão segura
    public static Prioridade fromString(String value) {
        if (value == null) return null;
        try {
            return Prioridade.valueOf(value.toUpperCase());
        } catch (IllegalArgumentException e) {
            throw new IllegalArgumentException("Prioridade inválida: " + value +
                    ". Valores aceitos: " + java.util.Arrays.toString(Prioridade.values()));
        }
    }
}