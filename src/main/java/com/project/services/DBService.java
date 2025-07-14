package com.project.services;

import com.project.domains.Tarefa;
import com.project.domains.Usuario;
import com.project.domains.enums.Prioridade;
import com.project.domains.enums.StatusTarefa;
import com.project.repositories.TarefaRepository;
import com.project.repositories.UsuarioRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
public class DBService {

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Autowired
    private TarefaRepository tarefaRepository;

    @Transactional
    public void initDB() {
        // 1. Criar e salvar usuários primeiro (para gerar IDs)
        Usuario usuario01 = new Usuario(
                "João Silva",
                "joao@email.com",
                "senha123");

        Usuario usuario02 = new Usuario(
                "Maria Souza",
                "maria@email.com",
                "senha456");

        usuarioRepository.save(usuario01); // Salva para gerar ID
        usuarioRepository.save(usuario02);

        // 2. Criar tarefas associando aos usuários já persistidos
        Tarefa tarefa01 = new Tarefa(
                null,
                "Estudar Java",
                "Revisar conceitos de Spring Boot",
                LocalDateTime.now(),
                Prioridade.ALTA,
                StatusTarefa.PENDENTE
        );

        tarefa01.setUsuario(usuario01); // Associa ao usuário persistido

        Tarefa tarefa02 = new Tarefa(
                null,
                "Fazer compras",
                "Comprar itens para o almoço",
                LocalDateTime.now(),
                Prioridade.MEDIA,
                StatusTarefa.PENDENTE
        );
        tarefa02.setUsuario(usuario01);

        Tarefa tarefa03 = new Tarefa(
                null,
                "Lavar o carro",
                "Levar ao lava-rápido",
                LocalDateTime.now(),
                Prioridade.BAIXA,
                StatusTarefa.PENDENTE
        );
        tarefa03.setUsuario(usuario02);

        Tarefa tarefa04 = new Tarefa(
                null,
                "Reunião com cliente",
                "Apresentar projeto final",
                LocalDateTime.now(),
                Prioridade.ALTA,
                StatusTarefa.PENDENTE
        );
        tarefa04.setUsuario(usuario02);

        // 3. Salvar tarefas
        tarefaRepository.save(tarefa01);
        tarefaRepository.save(tarefa02);
        tarefaRepository.save(tarefa03);
        tarefaRepository.save(tarefa04);
    }
}
