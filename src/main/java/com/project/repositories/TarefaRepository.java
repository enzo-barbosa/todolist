package com.project.repositories;

import com.project.domains.Tarefa;
import com.project.domains.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface TarefaRepository extends JpaRepository<Tarefa, Long> {

    Optional<Tarefa> findByTitulo(String titulo);

    // NOVO: Buscar tarefas por usuário
    List<Tarefa> findByUsuario(Usuario usuario);

    // NOVO: Buscar tarefa por ID e usuário (para segurança)
    Optional<Tarefa> findByIdAndUsuario(Long id, Usuario usuario);
}