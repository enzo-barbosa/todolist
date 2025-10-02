package com.project.services;

import com.project.domains.Tarefa;
import com.project.domains.Usuario;
import com.project.domains.enums.Prioridade;
import com.project.domains.enums.StatusTarefa;
import com.project.repositories.TarefaRepository;
import com.project.repositories.UsuarioRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
public class DBService {

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Autowired
    private TarefaRepository tarefaRepository;

    @Autowired
    private PasswordEncoder passwordEncoder; // ✅ NOVO: Injetamos o criptografador

    @Transactional
    public void initDB() {
        // 1. Criar usuários com senhas CRIPTOGRAFADAS
        Usuario usuario01 = new Usuario(
                "João Silva",
                "joao@email.com",
                "senha123");

        Usuario usuario02 = new Usuario(
                "Maria Souza",
                "maria@email.com",
                "senha456");

        // ✅ AGORA: Criptografamos as senhas antes de salvar
        usuario01.setSenhaCriptografada("senha123", passwordEncoder);
        usuario02.setSenhaCriptografada("senha456", passwordEncoder);

        usuarioRepository.save(usuario01);
        usuarioRepository.save(usuario02);

        // 2. Criar tarefas (mesmo código que você já tem)
        Tarefa tarefa01 = new Tarefa(
                null,
                "Estudar Java",
                "Revisar conceitos de Spring Boot",
                LocalDateTime.now(),
                Prioridade.ALTA,
                StatusTarefa.PENDENTE
        );
        tarefa01.setUsuario(usuario01);

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

        System.out.println("=== BANCO INICIALIZADO COM SUCESSO ===");
        System.out.println("Usuários criados:");
        System.out.println("👉 joao@email.com / senha123");
        System.out.println("👉 maria@email.com / senha456");
        System.out.println("=====================================");
    }
}