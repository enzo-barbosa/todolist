package com.project.domains;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.project.domains.enums.Prioridade;
import com.project.domains.enums.StatusTarefa;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import org.hibernate.annotations.CreationTimestamp;
import org.springframework.lang.NonNull;

import java.time.LocalDateTime;
import java.util.Objects;

@Entity
@Table(name = "tarefa")
public class Tarefa {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "seq_tarefa")
    private Long id;

    @NotBlank @NotNull
    private String titulo;

    @NotBlank @NotNull
    private String descricao;

    @CreationTimestamp
    @Column(updatable = false)
    private LocalDateTime dataCriacao;

    @Enumerated(EnumType.STRING)
    @Column(name = "prioridade")
    private Prioridade prioridade;

    @Enumerated(EnumType.STRING)
    @Column(name = "status")
    private StatusTarefa status;

    @ManyToOne
    @JoinColumn(name = "usuario_id")
    private Usuario usuario;

    public Tarefa() {
        this.status = StatusTarefa.PENDENTE;
    }

    public Tarefa(Long id, String titulo, String descricao, LocalDateTime dataCriacao, Prioridade prioridade, StatusTarefa status) {
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

    public Usuario getUsuario() {
        return this.usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
        if (usuario != null && !usuario.getTarefas().contains(this)) {
            usuario.getTarefas().add(this); // Atualiza o lado inverso
        }
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        Tarefa tarefa = (Tarefa) o;
        return Objects.equals(id, tarefa.id) && Objects.equals(dataCriacao, tarefa.dataCriacao);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, dataCriacao);
    }
}
