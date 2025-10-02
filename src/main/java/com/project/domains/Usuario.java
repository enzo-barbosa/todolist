package com.project.domains;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.project.domains.dtos.UsuarioDTO;
import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

// Entidade JPA que representa um usuário no sistema
@Entity
@Table(name = "usuario")
public class Usuario {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "seq_usuario")
    private Long id;

    @NotBlank @NotNull
    private String nome;

    @Email // Valida formato de email
    @Column(unique = true) // Garante unicidade no banco
    @NotBlank @NotNull
    private String email;

    @NotBlank @NotNull
    private String senha;

    // Relacionamento um-para-muitos com tarefas
    @OneToMany(mappedBy = "usuario", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Tarefa> tarefas = new ArrayList<>();

    public Usuario() {
    }

    public Usuario(String nome, String email, String senha) {
        this.nome = nome;
        this.email = email;
        this.senha = senha;
    }

    public Usuario(UsuarioDTO dto) {
        this.nome = dto.getNome();
        this.email = dto.getEmail();
        this.senha = dto.getSenha();
    }

    // Callback para normalizar email antes de persistir/atualizar
    @PrePersist
    @PreUpdate
    private void prepare() {
        this.email = email.toLowerCase().trim(); // Padroniza formato do email
    }

    // GETTERS E SETTERS
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getNome() { return nome; }
    public void setNome(String nome) { this.nome = nome; }

    public String getEmail() { return email; }
    public void setEmail(String email) { this.email = email; }

    @JsonIgnore // Evita que a senha seja serializada em JSON
    public String getSenha() { return senha; }

    // Setter para senha sem criptografia (usado quando já está criptografada)
    public void setSenha(String senha) {
        this.senha = senha;
    }

    // Método para criptografar a senha antes de salvar
    public void setSenhaCriptografada(String senha, org.springframework.security.crypto.password.PasswordEncoder passwordEncoder) {
        this.senha = passwordEncoder.encode(senha);
    }

    // Método para verificar se senha plain text corresponde à senha criptografada
    public boolean verificarSenha(String senhaPlain, org.springframework.security.crypto.password.PasswordEncoder passwordEncoder) {
        return passwordEncoder.matches(senhaPlain, this.senha);
    }

    public List<Tarefa> getTarefas() { return tarefas; }
    public void setTarefas(List<Tarefa> tarefas) { this.tarefas = tarefas; }

    // Equals e HashCode baseados em id e email
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Usuario usuario = (Usuario) o;
        return Objects.equals(id, usuario.id) && Objects.equals(email, usuario.email);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, email);
    }
}