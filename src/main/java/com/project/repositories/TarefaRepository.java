package com.project.repositories;

import com.project.domains.Tarefa;
import com.project.domains.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

// Repository para operações de banco com a entidade Tarefa
@Repository
public interface TarefaRepository extends JpaRepository<Tarefa, Long> {

    // Busca uma tarefa pelo título exato (case sensitive)
    Optional<Tarefa> findByTitulo(String titulo);

    // Busca todas as tarefas de um usuário específico
    List<Tarefa> findByUsuario(Usuario usuario);

    // Busca uma tarefa específica por ID apenas se pertencer ao usuário
    // Usado para validação de segurança
    Optional<Tarefa> findByIdAndUsuario(Long id, Usuario usuario);
}