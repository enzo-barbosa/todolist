package com.project.services;

import com.project.domains.dtos.TarefaDTO;
import com.project.repositories.TarefaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
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
}
