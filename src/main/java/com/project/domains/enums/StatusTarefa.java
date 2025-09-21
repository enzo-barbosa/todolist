package com.project.domains.enums;

public enum StatusTarefa {
    PENDENTE,
    CONCLUIDA,
    ARQUIVADA;

    // Método auxiliar para conversão segura (opcional)
    public static StatusTarefa fromString(String value) {
        if (value == null) return null;
        try {
            return StatusTarefa.valueOf(value.toUpperCase());
        } catch (IllegalArgumentException e) {
            throw new IllegalArgumentException("Status inválido: " + value +
                    ". Valores aceitos: " + java.util.Arrays.toString(StatusTarefa.values()));
        }
    }
}