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

// Controller REST para operações com usuários
@RestController
@RequestMapping(value = "/usuario") // Base path para todos os endpoints
@Tag(name = "Usuários", description = "API para gerenciamento de Usuários")
public class UsuarioResource {

    @Autowired
    private UsuarioService usuarioService; // Injeção do serviço de usuários

    // Endpoint para listar todos os usuários
    @GetMapping // http://localhost:8080/usuario
    @Operation(summary = "Lista todos os Usuários",
            description = "Retorna uma lista com todos os Usuários cadastrados")
    public ResponseEntity<List<UsuarioDTO>> findAll() {
        return ResponseEntity.ok().body(usuarioService.findAll());
    }

    // Endpoint para buscar usuário por ID
    @GetMapping(value = "/{id}") // http://localhost:8080/usuario/1
    @Operation(summary = "Busca usuário por ID",
            description = "Realiza a busca de um usuário cadastrado pelo seu ID")
    public ResponseEntity<UsuarioDTO> findById(@PathVariable Long id) {
        Usuario obj = this.usuarioService.findById(id);
        return ResponseEntity.ok().body(new UsuarioDTO(obj));
    }

    // Endpoint para buscar usuário por email
    @GetMapping(value = "/email/{email}") // http://localhost:8080/usuario/email@exemplo.com
    @Operation(summary = "Busca usuário por e-mail",
            description = "Realiza a busca de um usuário cadastrado pelo seu e-mail")
    public ResponseEntity<UsuarioDTO> findByEmail(@PathVariable String email) {
        Usuario obj = this.usuarioService.findByEmail(email);
        return ResponseEntity.ok().body(new UsuarioDTO(obj));
    }

    // Endpoint para criar novo usuário
    @PostMapping
    @Operation(summary = "Cria um novo usuário",
            description = "Cria um novo usuário com base nos dados fornecidos")
    public ResponseEntity<UsuarioDTO> create(@Valid @RequestBody UsuarioDTO dto) {
        Usuario usuario = usuarioService.create(dto);
        // Cria URI do recurso criado
        URI uri = ServletUriComponentsBuilder.fromCurrentRequest()
                .path("/{id}").buildAndExpand(usuario.getId()).toUri();

        return ResponseEntity.created(uri).body(new UsuarioDTO(usuario));
    }

    // Endpoint para atualizar usuário existente
    @PutMapping(value = "/{id}")
    @Operation(summary = "Atualiza um usuário",
            description = "Atualiza os dados de um usuário existente")
    public ResponseEntity<UsuarioDTO> update(@PathVariable Long id, @Valid @RequestBody UsuarioDTO objDto) {
        Usuario obj = usuarioService.update(id, objDto);
        return ResponseEntity.ok().body(new UsuarioDTO(obj));
    }

    // Endpoint para deletar usuário
    @DeleteMapping(value = "/{id}")
    @Operation(summary = "Deleta um usuário",
            description = "Remove um usuário do sistema a partir do seu ID")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        usuarioService.delete(id);
        return ResponseEntity.noContent().build(); // Retorna 204 No Content
    }
}