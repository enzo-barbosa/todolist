package com.project.resources;

import com.project.domains.Tarefa;
import com.project.domains.dtos.TarefaDTO;
import com.project.services.TarefaService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;

// Controller REST para operações com tarefas
@RestController
@RequestMapping(value = "/tarefa") // Base path para todos os endpoints
@Tag(name = "Tarefas", description = "API para gerenciamento de Tarefas")
public class TarefaResource {

    @Autowired
    private TarefaService tarefaService; // Injeção do serviço de tarefas

    // Endpoint para listar todas as tarefas
    @GetMapping // http://localhost:8080/tarefa
    @Operation(summary = "Lista todas as Tarefas",
            description = "Retorna uma lista com todas as Tarefas cadastradas no sistema")
    public ResponseEntity<List<TarefaDTO>> findAll() {
        return ResponseEntity.ok().body(tarefaService.findAll());
    }

    // Endpoint para buscar tarefa por ID
    @GetMapping(value = "/{id}") // http://localhost:8080/tarefa/1
    @Operation(summary = "Busca tarefa por ID",
            description = "Realiza a busca de uma tarefa cadastrada pelo seu ID único")
    public ResponseEntity<TarefaDTO> findById(@PathVariable Long id) {
        Tarefa obj = this.tarefaService.findById(id);
        return ResponseEntity.ok().body(new TarefaDTO(obj));
    }

    // Endpoint para buscar tarefa por título exato
    @GetMapping(value = "/titulo/{titulo}") // http://localhost:8080/tarefa/titulo/estudar
    @Operation(summary = "Busca tarefa por título",
            description = "Realiza a busca de uma tarefa cadastrada pelo seu título exato")
    public ResponseEntity<TarefaDTO> findByTitulo(@PathVariable String titulo) {
        Tarefa obj = this.tarefaService.findByTitulo(titulo);
        return ResponseEntity.ok().body(new TarefaDTO(obj));
    }

    // Endpoint para criar nova tarefa
    @PostMapping
    @Operation(summary = "Cria uma nova tarefa",
            description = "Cria uma nova tarefa no sistema associada a um usuário")
    public ResponseEntity<TarefaDTO> create(@Valid @RequestBody TarefaDTO dto) {
        Tarefa tarefa = tarefaService.create(dto);
        // Cria URI do recurso criado para retornar no header Location
        URI uri = ServletUriComponentsBuilder.fromCurrentRequest()
                .path("/{id}").buildAndExpand(tarefa.getId()).toUri();

        return ResponseEntity.created(uri).body(new TarefaDTO(tarefa));
    }

    // Endpoint para atualizar tarefa existente
    @PutMapping(value = "/{id}")
    @Operation(summary = "Atualiza uma tarefa existente",
            description = "Atualiza os dados de uma tarefa existente no sistema")
    public ResponseEntity<TarefaDTO> update(@PathVariable Long id, @Valid @RequestBody TarefaDTO objDto) {
        Tarefa obj = tarefaService.update(id, objDto);
        return ResponseEntity.ok().body(new TarefaDTO(obj));
    }

    // Endpoint para deletar tarefa
    @DeleteMapping(value = "/{id}")
    @Operation(summary = "Remove uma tarefa",
            description = "Remove permanentemente uma tarefa do sistema a partir do seu ID")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        tarefaService.delete(id);
        return ResponseEntity.noContent().build(); // Retorna 204 No Content
    }
}