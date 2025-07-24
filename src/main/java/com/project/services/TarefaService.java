package com.project.services;

import com.project.domains.Tarefa;
import com.project.domains.Usuario;
import com.project.domains.dtos.TarefaDTO;
import com.project.repositories.TarefaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class TarefaService {

    @Autowired
    private TarefaRepository tarefaRepo;

    public List<TarefaDTO> findAll() {
        // retorna uma lista de TarefaDTO
        return tarefaRepo.findAll().stream()
                .map(obj -> new TarefaDTO(obj))
                .collect(Collectors.toList());
    }

    public Tarefa findById(Long id) {
        Optional<Tarefa> obj = tarefaRepo.findById(id);
        return obj.orElse(null);
    }

    public Tarefa findByTitulo(String titulo) {
        Optional<Tarefa> obj = tarefaRepo.findByTitulo(titulo);
        return obj.orElse(null);
    }
}
