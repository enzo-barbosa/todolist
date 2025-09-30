package com.project.resources;

import com.project.domains.Usuario;
import com.project.domains.dtos.UsuarioDTO;
import com.project.services.UsuarioService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping(value = "/usuario")
@Tag(name = "Usuários", description = "API para gerenciamento de Usuários")
public class UsuarioResource {

    @Autowired
    private UsuarioService usuarioService;

    @GetMapping // exemplo http://localhost:8080/tarefa
    @Operation(summary = "Lista todos os Usuários",
            description = "Retorna uma lista com todos os Usuários cadastrados")
    public ResponseEntity<List<UsuarioDTO>> findAll() {
        return ResponseEntity.ok().body(usuarioService.findAll());
    }

    @GetMapping(value = "/{id}") //exemplo http://localhost:8080/usuario/1
    @Operation(summary = "Busca usuário por ID",
            description = "Realiza a busca de um usuário cadastrado pelo seu ID")
    public ResponseEntity<UsuarioDTO> findById(@PathVariable Long id) {
        Usuario obj = this.usuarioService.findById(id);
        return ResponseEntity.ok().body(new UsuarioDTO(obj));
    }

    @GetMapping(value = "/email/{email}") //exemplo http://localhost:8080/usuario/enzo@gmail.com
    @Operation(summary = "Busca usuário por e-mail",
            description = "Realiza a busca de um usuário cadastrado pelo seu e-mail")
    public ResponseEntity<UsuarioDTO> findByEmail(@PathVariable String email) {
        Usuario obj = this.usuarioService.findByEmail(email);
        return ResponseEntity.ok().body(new UsuarioDTO(obj));
    }

    @PostMapping
    @Operation(summary = "Cria um novo usuário",
            description = "Cria um novo usuário com base nos dados fornecidos")
    public ResponseEntity<UsuarioDTO> create(@Valid @RequestBody UsuarioDTO dto) {
        Usuario usuario = usuarioService.create(dto);
        URI uri = ServletUriComponentsBuilder.fromCurrentRequest()
                .path("/{id}").buildAndExpand(usuario.getId()).toUri();

        // Retorna o DTO do usuário criado no corpo da resposta
        return ResponseEntity.created(uri).body(new UsuarioDTO(usuario));
    }

    @PutMapping(value = "/{id}")
    @Operation(summary = "Atualiza um usuário",
            description = "Atualiza os dados de um usuário existente")
    public ResponseEntity<UsuarioDTO> update(@PathVariable Long id, @Valid @RequestBody UsuarioDTO objDto) {
        Usuario obj = usuarioService.update(id, objDto);
        return ResponseEntity.ok().body(new UsuarioDTO(obj));
    }

    @DeleteMapping(value = "/{id}")
    @Operation(summary = "Deleta um usuário",
            description = "Remove um usuário do sistema a partir do seu ID")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        usuarioService.delete(id);
        return ResponseEntity.noContent().build();
    }
}