package com.project.resources;

import com.project.domains.Tarefa;
import com.project.domains.dtos.TarefaDTO;
import com.project.services.TarefaService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
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
    public ResponseEntity<TarefaDTO> findByTitulo(@PathVariable String titulo) {
        Tarefa obj = this.tarefaService.findByTitulo(titulo);
        return ResponseEntity.ok().body(new TarefaDTO(obj));
    }

    @PostMapping
    public ResponseEntity<TarefaDTO> create(@Valid @RequestBody TarefaDTO dto) {
        Tarefa tarefa = tarefaService.create(dto);
        URI uri = ServletUriComponentsBuilder.fromCurrentRequest()
                .path("/{id}").buildAndExpand(tarefa.getId()).toUri();

        // Retorna o DTO do usuário criado no corpo da resposta
        return ResponseEntity.created(uri).body(new TarefaDTO(tarefa));
    }

    @PutMapping(value = "/{id}")
    public ResponseEntity<TarefaDTO> update(@PathVariable Long id, @Valid @RequestBody TarefaDTO objDto) {
        Tarefa obj = tarefaService.update(id, objDto);
        return ResponseEntity.ok().body(new TarefaDTO(obj));
    }

    @DeleteMapping(value = "/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        tarefaService.delete(id);
        return ResponseEntity.noContent().build();
    }
}
