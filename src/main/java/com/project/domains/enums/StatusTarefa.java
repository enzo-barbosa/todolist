package com.project.domains.enums;

public enum StatusTarefa {
    PENDENTE(1, "PENDENTE"),
    CONCLUIDA(2, "CONCLUIDA"),
    ARQUIVADA(3, "ARQUIVADA");

    private final Integer id;
    private final String descricao;

    StatusTarefa(Integer id, String descricao) {
        this.id = id;
        this.descricao = descricao;
    }

    public Integer getId() {
        return id;
    }

    public String getDescricao() {
        return descricao;
    }

    public static StatusTarefa toEnum(Integer id) {
        if (id==null) return null;
        for (StatusTarefa x : StatusTarefa.values()) {
            if (id.equals(x.getId())) {
                return x;
            }
        }
        throw new IllegalArgumentException("Status Inválido. ID:" + id +
                ". Valores aceitos: 1 (PENDENTE), 2 (CONCLUIDA), 3(ARQUIVADA)");
    }
}
