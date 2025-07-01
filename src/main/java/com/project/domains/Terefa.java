package com.project.domains;

import com.project.domains.enums.Prioridade;
import com.project.domains.enums.StatusTarefa;

import java.time.LocalDateTime;
import java.util.Objects;

public class Terefa {
    private Long id;
    private String titulo;
    private String descricao;
    private LocalDateTime dataCriacao;
    private Prioridade prioridade;
    private StatusTarefa status;

    public Terefa() {
    }

    public Terefa(Long id, String titulo, String descricao, LocalDateTime dataCriacao, Prioridade prioridade, StatusTarefa status) {
        this.id = id;
        this.titulo = titulo;
        this.descricao = descricao;
        this.dataCriacao = dataCriacao;
        this.prioridade = prioridade;
        this.status = status;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public LocalDateTime getDataCriacao() {
        return dataCriacao;
    }

    public void setDataCriacao(LocalDateTime dataCriacao) {
        this.dataCriacao = dataCriacao;
    }

    public Prioridade getPrioridade() {
        return prioridade;
    }

    public void setPrioridade(Prioridade prioridade) {
        this.prioridade = prioridade;
    }

    public StatusTarefa getStatus() {
        return status;
    }

    public void setStatus(StatusTarefa status) {
        this.status = status;
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        Terefa terefa = (Terefa) o;
        return Objects.equals(id, terefa.id) && Objects.equals(dataCriacao, terefa.dataCriacao);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, dataCriacao);
    }
}
