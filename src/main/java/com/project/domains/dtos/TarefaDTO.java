package com.project.domains.dtos;

import com.project.domains.Tarefa;
import com.project.domains.enums.Prioridade;
import com.project.domains.enums.StatusTarefa;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.time.LocalDateTime;

public class TarefaDTO {

    private Long id;

    @NotNull(message = "O título não pode ser nulo")
    @NotBlank(message = "O título não pode ser vazio")
    private String titulo;

    @NotNull(message = "A descrição não pode ser nula")
    @NotBlank(message = "A descrição não pode ser vazia")
    private String descricao;

    @NotNull(message = "A prioridade não pode ser nula")
    private Prioridade prioridade;

    @NotNull(message = "O status não pode ser nulo")
    private StatusTarefa status;

    @NotNull(message = "O usuario não pode ser nulo")
    private Long usuarioId;

    public TarefaDTO() {
    }

    public TarefaDTO(Tarefa tarefa) {
        this.id = tarefa.getId();
        this.titulo = tarefa.getTitulo();
        this.descricao = tarefa.getDescricao();
        this.prioridade = tarefa.getPrioridade();
        this.status = tarefa.getStatus();
        this.usuarioId = tarefa.getUsuario().getId();
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

    public Long getUsuarioId() {
        return usuarioId;
    }

    public void setUsuarioId(Long usuarioId) {
        this.usuarioId = usuarioId;
    }
}
