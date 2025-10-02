package com.project.domains.dtos;

import com.project.domains.Usuario;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

// DTO para transferência de dados de Usuário entre camadas
public class UsuarioDTO {

    private Long id;

    @NotNull(message = "O nome não pode ser nulo")
    @NotBlank(message = "O nome não pode ser vazio")
    private String nome;

    @NotNull(message = "O email não pode ser nulo")
    @NotBlank(message = "O email não pode ser vazio")
    @Email(message = "Email deve ser válido")
    private String email;

    @NotNull(message = "A senha não pode ser nulo")
    @NotBlank(message = "A senha não pode ser vazio")
    @Size(min = 6, message = "Senha deve ter no mínimo 6 caracteres")
    private String senha;

    public UsuarioDTO() {
    }

    // Construtor que converte entidade Usuario para DTO (sem expor senha)
    public UsuarioDTO(Usuario usuario) {
        this.id = usuario.getId();
        this.nome = usuario.getNome();
        this.email = usuario.getEmail();
        // Senha não é incluída por segurança
    }

    // GETTERS E SETTERS
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getNome() { return nome; }
    public void setNome(String nome) { this.nome = nome; }

    public String getEmail() { return email; }
    public void setEmail(String email) { this.email = email; }

    public String getSenha() { return senha; }
    public void setSenha(String senha) { this.senha = senha; }
}