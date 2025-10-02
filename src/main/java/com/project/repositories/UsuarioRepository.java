package com.project.repositories;

import com.project.domains.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

// Repository para operações de banco com a entidade Usuario
@Repository
public interface UsuarioRepository extends JpaRepository<Usuario, Long> {

    // Busca um usuário pelo email (único no sistema)
    Optional<Usuario> findByEmail(String email);
}