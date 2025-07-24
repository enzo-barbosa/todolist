package com.project.resources;

import com.project.domains.Tarefa;
import com.project.domains.dtos.TarefaDTO;
import com.project.services.TarefaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
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

    @GetMapping(value = "/{id}") //exemplo http://localhost:8080/tarefa/1
    public ResponseEntity<TarefaDTO> findById(@PathVariable Long id) {
        Tarefa obj = this.tarefaService.findById(id);
        return ResponseEntity.ok().body(new TarefaDTO(obj));
    }

    @GetMapping(value = "/titulo/{titulo}") //exemplo http://localhost:8080/tarefa/titulo/1
    public ResponseEntity<TarefaDTO> findById(@PathVariable String titulo) {
        Tarefa obj = this.tarefaService.findByTitulo(titulo);
        return ResponseEntity.ok().body(new TarefaDTO(obj));
    }
}
