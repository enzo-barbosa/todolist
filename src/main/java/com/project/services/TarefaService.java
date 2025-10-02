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

@Service
public class TarefaService {

    @Autowired
    private TarefaRepository tarefaRepo;

    @Autowired
    private UsuarioService usuarioService;

    @Autowired
    private UsuarioRepository usuarioRepository;

    public List<TarefaDTO> findAll() {
        return tarefaRepo.findAll().stream()
                .map(obj -> new TarefaDTO(obj))
                .collect(Collectors.toList());
    }

    public Tarefa findById(Long id) {
        Optional<Tarefa> obj = tarefaRepo.findById(id);
        return obj.orElseThrow(() -> new ObjectNotFoundException("Tarefa não encontrada! Id: " + id));
    }

    // NOVO: Buscar tarefa com segurança (verifica se pertence ao usuário)
    public Tarefa findByIdAndUsuario(Long id, Usuario usuario) {
        Optional<Tarefa> obj = tarefaRepo.findByIdAndUsuario(id, usuario);
        return obj.orElseThrow(() -> new ObjectNotFoundException("Tarefa não encontrada ou acesso negado! Id: " + id));
    }

    public Tarefa findByTitulo(String titulo) {
        Optional<Tarefa> obj = tarefaRepo.findByTitulo(titulo);
        return obj.orElseThrow(() -> new ObjectNotFoundException("Tarefa não encontrada! Título: " + titulo));
    }

    public Tarefa create(TarefaDTO dto) {
        dto.setId(null);

        if (dto.getPrioridade() == null) {
            throw new IllegalArgumentException("Prioridade é obrigatória");
        }

        //  TEMPORÁRIO: Usa o João como usuário logado
        // Depois implementamos o usuário REAL da sessão
        Usuario usuario = usuarioService.findById(1L); // João Silva

        Tarefa obj = new Tarefa(dto, usuario);
        return tarefaRepo.save(obj);
    }

    public Tarefa update(Long id, TarefaDTO objDto) {
        Tarefa existingTarefa = findById(id);
        if (existingTarefa == null) {
            throw new ObjectNotFoundException("Tarefa não encontrada com ID: " + id);
        }

        existingTarefa.setTitulo(objDto.getTitulo());
        existingTarefa.setDescricao(objDto.getDescricao());
        existingTarefa.setPrioridade(objDto.getPrioridade());
        existingTarefa.setStatus(objDto.getStatus());

        return tarefaRepo.save(existingTarefa);
    }

    public void delete(Long id) {
        Tarefa obj = findById(id);
        tarefaRepo.deleteById(id);
    }
}