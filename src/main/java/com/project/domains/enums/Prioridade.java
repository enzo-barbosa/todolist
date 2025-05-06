package com.project.domains.enums;

public enum Prioridade {
    BAIXA(1, "BAIXA"),
    MEDIA(2, "MEDIA"),
    ALTA(3, "ALTA");

    private final Integer id;
    private final String descricao;

    Prioridade(Integer id, String descricao) {
        this.id = id;
        this.descricao = descricao;
    }

    public Integer getId() {
        return id;
    }

    public String getDescricao() {
        return descricao;
    }

    public static Prioridade toEnum(Integer id) {
        if (id==null) return null;
        for (Prioridade x : Prioridade.values()) {
            if (id.equals(x.getId())) {
                return x;
            }
        }
        throw new IllegalArgumentException("Prioridade Inválida:" + id +
                ". Valores aceitos: 1 (BAIXA), 2 (MÉDIA), 3 (ALTA)");
    }
}
