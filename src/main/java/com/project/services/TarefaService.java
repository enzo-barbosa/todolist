package com.project.services;

import com.project.domains.Tarefa;
import com.project.domains.Usuario;
import com.project.domains.dtos.TarefaDTO;
import com.project.repositories.TarefaRepository;
import com.project.services.exceptions.ObjectNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class TarefaService {

    @Autowired
    private TarefaRepository tarefaRepo;

    @Autowired
    private UsuarioService usuarioService;

    public List<TarefaDTO> findAll() {
        // retorna uma lista de TarefaDTO
        return tarefaRepo.findAll().stream()
                .map(obj -> new TarefaDTO(obj))
                .collect(Collectors.toList());
    }

    public Tarefa findById(Long id) {
        Optional<Tarefa> obj = tarefaRepo.findById(id);
        return obj.orElseThrow(() -> new ObjectNotFoundException("Tarefa não encontrada! Id: " + id));
    }

    public Tarefa findByTitulo(String titulo) {
        Optional<Tarefa> obj = tarefaRepo.findByTitulo(titulo);
        return obj.orElseThrow(() -> new ObjectNotFoundException("Tarefa não encontrada! Título: " + titulo));
    }

    public Tarefa create(TarefaDTO dto) {
        dto.setId(null);

        // Valida se prioridade foi informada
        if (dto.getPrioridade() == null) {
            throw new IllegalArgumentException("Prioridade é obrigatória");
        }

        // Busca o usuário
        Usuario usuario = usuarioService.findById(dto.getUsuarioId());
        if (usuario == null) {
            throw new RuntimeException("Usuário não encontrado com ID: " + dto.getUsuarioId());
        }

        Tarefa obj = new Tarefa(dto, usuario);
        return tarefaRepo.save(obj);
    }

    public Tarefa update(Long id, TarefaDTO objDto) {
        Tarefa existingTarefa = findById(id);
        if (existingTarefa == null) {
            throw new ObjectNotFoundException("Tarefa não encontrada com ID: " + id);
        }

        // Atualiza campos
        existingTarefa.setTitulo(objDto.getTitulo());
        existingTarefa.setDescricao(objDto.getDescricao());
        existingTarefa.setPrioridade(objDto.getPrioridade());
        existingTarefa.setStatus(objDto.getStatus()); // ✅ ADICIONAR

        // IMPORTANTE: Atualizar usuário apenas se fornecido
        if (objDto.getUsuarioId() != null) {
            Usuario novoUsuario = usuarioService.findById(objDto.getUsuarioId());
            if (novoUsuario != null) {
                existingTarefa.setUsuario(novoUsuario);
            }
        }

        return tarefaRepo.save(existingTarefa);
    }
}
