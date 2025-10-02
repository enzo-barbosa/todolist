package com.project.domains;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.project.domains.dtos.TarefaDTO;
import com.project.domains.enums.Prioridade;
import com.project.domains.enums.StatusTarefa;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import org.hibernate.annotations.CreationTimestamp;
import org.springframework.lang.NonNull;

import java.time.LocalDateTime;
import java.util.Objects;

// Entidade JPA que representa uma tarefa no sistema
@Entity
@Table(name = "tarefa") // Nome da tabela no banco
public class Tarefa {

    @Id // Chave primária
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "seq_tarefa") // Geração sequencial
    private Long id;

    @NotBlank @NotNull // Validações: não pode ser nulo ou vazio
    private String titulo;

    @NotBlank @NotNull
    private String descricao;

    @CreationTimestamp // Hibernate preenche automaticamente com data/hora atual
    @Column(updatable = false) // Não pode ser atualizado após criação
    private LocalDateTime dataCriacao;

    @Enumerated(EnumType.STRING) // Armazena o enum como string no banco
    @Column(name = "prioridade")
    private Prioridade prioridade;

    @Enumerated(EnumType.STRING)
    @Column(name = "status")
    private StatusTarefa status;

    @ManyToOne // Muitas tarefas para um usuário
    @JoinColumn(name = "usuario_id") // Chave estrangeira para usuário
    private Usuario usuario;

    // Construtor padrão - inicializa status como PENDENTE
    public Tarefa() {
        this.status = StatusTarefa.PENDENTE;
    }

    // Construtor completo
    public Tarefa(Long id, String titulo, String descricao, LocalDateTime dataCriacao, Prioridade prioridade, StatusTarefa status) {
        this.id = id;
        this.titulo = titulo;
        this.descricao = descricao;
        this.dataCriacao = dataCriacao;
        this.prioridade = prioridade;
        this.status = status;
    }

    // Construtor a partir de DTO
    public Tarefa(TarefaDTO dto, Usuario usuario) {
        this.titulo = dto.getTitulo();
        this.descricao = dto.getDescricao();
        this.prioridade = dto.getPrioridade();
        this.status = dto.getStatus();
        this.usuario = usuario;
    }

    // Callback executado antes de persistir a entidade
    @PrePersist
    public void prePersist() {
        if (this.dataCriacao == null) {
            this.dataCriacao = LocalDateTime.now(); // Garante data de criação
        }
        if (this.status == null) {
            this.status = StatusTarefa.PENDENTE; // Garante status padrão
        }
    }

    // GETTERS E SETTERS
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getTitulo() { return titulo; }
    public void setTitulo(String titulo) { this.titulo = titulo; }

    public String getDescricao() { return descricao; }
    public void setDescricao(String descricao) { this.descricao = descricao; }

    public LocalDateTime getDataCriacao() { return dataCriacao; }
    public void setDataCriacao(LocalDateTime dataCriacao) { this.dataCriacao = dataCriacao; }

    public Prioridade getPrioridade() { return prioridade; }
    public void setPrioridade(Prioridade prioridade) { this.prioridade = prioridade; }

    public StatusTarefa getStatus() { return status; }
    public void setStatus(StatusTarefa status) { this.status = status; }

    public Usuario getUsuario() { return this.usuario; }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
        // Mantém a consistência bidirecional do relacionamento
        if (usuario != null && !usuario.getTarefas().contains(this)) {
            usuario.getTarefas().add(this);
        }
    }

    // Equals e HashCode baseados em id e dataCriacao
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