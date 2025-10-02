package com.project.services;

import com.project.domains.Tarefa;
import com.project.domains.Usuario;
import com.project.domains.dtos.TarefaDTO;
import com.project.repositories.TarefaRepository;
import com.project.repositories.UsuarioRepository;
import com.project.services.exceptions.ObjectNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

// Serviço de negócio para operações com tarefas
@Service
public class TarefaService {

    @Autowired
    private TarefaRepository tarefaRepo;

    @Autowired
    private UsuarioService usuarioService;

    @Autowired
    private UsuarioRepository usuarioRepository;

    // Retorna todas as tarefas convertidas para DTO
    public List<TarefaDTO> findAll() {
        return tarefaRepo.findAll().stream()
                .map(obj -> new TarefaDTO(obj))
                .collect(Collectors.toList());
    }

    // Busca tarefa por ID, lança exceção se não encontrada
    public Tarefa findById(Long id) {
        Optional<Tarefa> obj = tarefaRepo.findById(id);
        return obj.orElseThrow(() -> new ObjectNotFoundException("Tarefa não encontrada! Id: " + id));
    }

    // Busca tarefa por ID apenas se pertencer ao usuário específico (segurança)
    public Tarefa findByIdAndUsuario(Long id, Usuario usuario) {
        Optional<Tarefa> obj = tarefaRepo.findByIdAndUsuario(id, usuario);
        return obj.orElseThrow(() -> new ObjectNotFoundException("Tarefa não encontrada ou acesso negado! Id: " + id));
    }

    // Busca tarefa por título exato
    public Tarefa findByTitulo(String titulo) {
        Optional<Tarefa> obj = tarefaRepo.findByTitulo(titulo);
        return obj.orElseThrow(() -> new ObjectNotFoundException("Tarefa não encontrada! Título: " + titulo));
    }

    // Cria nova tarefa a partir do DTO
    public Tarefa create(TarefaDTO dto) {
        dto.setId(null); // Garante que é uma nova entidade

        // Validação de prioridade obrigatória
        if (dto.getPrioridade() == null) {
            throw new IllegalArgumentException("Prioridade é obrigatória");
        }

        // TEMPORÁRIO: Usa usuário fixo (ID 1 - João)
        // Em produção, deveria usar o usuário autenticado
        Usuario usuario = usuarioService.findById(1L); // João Silva

        // Cria entidade Tarefa a partir do DTO e usuário
        Tarefa obj = new Tarefa(dto, usuario);
        return tarefaRepo.save(obj);
    }

    // Atualiza tarefa existente
    public Tarefa update(Long id, TarefaDTO objDto) {
        // Busca tarefa existente (já valida se existe)
        Tarefa existingTarefa = findById(id);
        if (existingTarefa == null) {
            throw new ObjectNotFoundException("Tarefa não encontrada com ID: " + id);
        }

        // Atualiza campos da tarefa existente
        existingTarefa.setTitulo(objDto.getTitulo());
        existingTarefa.setDescricao(objDto.getDescricao());
        existingTarefa.setPrioridade(objDto.getPrioridade());
        existingTarefa.setStatus(objDto.getStatus());

        return tarefaRepo.save(existingTarefa);
    }

    // Remove tarefa do sistema
    public void delete(Long id) {
        Tarefa obj = findById(id); // Valida existência antes de deletar
        tarefaRepo.deleteById(id);
    }
}