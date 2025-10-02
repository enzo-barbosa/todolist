package com.project.services;

import com.project.domains.Usuario;
import com.project.domains.dtos.UsuarioDTO;
import com.project.repositories.UsuarioRepository;
import com.project.services.exceptions.ObjectNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

// Serviço de negócio para operações com usuários
@Service
public class UsuarioService {

    @Autowired
    private UsuarioRepository usuarioRepo;

    // Retorna todos os usuários convertidos para DTO
    public List<UsuarioDTO> findAll() {
        return usuarioRepo.findAll().stream()
                .map(obj -> new UsuarioDTO(obj))
                .collect(Collectors.toList());
    }

    // Busca usuário por ID, retorna null se não encontrado
    public Usuario findById(Long id) {
        Optional<Usuario> obj = usuarioRepo.findById(id);
        return obj.orElse(null);
    }

    // Busca usuário por email, lança exceção se não encontrado
    public Usuario findByEmail(String email) {
        Optional<Usuario> obj = usuarioRepo.findByEmail(email);
        return obj.orElseThrow(() -> new ObjectNotFoundException("Email não encontrado! Email: " + email));
    }

    // Cria novo usuário a partir do DTO
    public Usuario create(UsuarioDTO dto) {
        dto.setId(null); // Garante que é uma nova entidade

        // Verifica se email já está cadastrado
        if(usuarioRepo.findByEmail(dto.getEmail()).isPresent()) {
            throw new DataIntegrityViolationException("E-mail já cadastrado");
        }

        // Cria entidade Usuario a partir do DTO
        Usuario obj = new Usuario(dto);
        return usuarioRepo.save(obj);
    }

    // Atualiza usuário existente
    public Usuario update(Long id, UsuarioDTO objDto) {
        Usuario existingUsuario = findById(id);
        if (existingUsuario == null) {
            throw new ObjectNotFoundException("Usuário não encontrado com ID: " + id);
        }

        // Atualiza apenas campos básicos (nome e email)
        existingUsuario.setNome(objDto.getNome());
        existingUsuario.setEmail(objDto.getEmail());

        return usuarioRepo.save(existingUsuario);
    }

    // Remove usuário do sistema
    public void delete(Long id) {
        Usuario obj = findById(id);

        if (obj == null) {
            throw new ObjectNotFoundException("Usuário não encontrado com ID: " + id);
        }

        // Impede deleção se usuário tem tarefas vinculadas
        if (!obj.getTarefas().isEmpty()) {
            throw new IllegalStateException(
                    "Usuário não pode ser deletado pois possui " +
                            obj.getTarefas().size() + " tarefas vinculadas!"
            );
        }

        usuarioRepo.deleteById(id);
    }
}