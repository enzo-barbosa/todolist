package com.project.resources;

import com.project.domains.dtos.TarefaDTO;
import com.project.services.TarefaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping(value = "/tarefa")
public class TarefaResource {

    @Autowired
    private TarefaService tarefaService;

    @GetMapping //exemplo http://localhost:8080/tarefa
    public ResponseEntity<List<TarefaDTO>> findAll() {
        return ResponseEntity.ok().body(tarefaService.findAll());
    }
}
