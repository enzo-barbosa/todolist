package com.project.services;

import com.project.domains.Tarefa;
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

@Service
public class UsuarioService {

    @Autowired
    private UsuarioRepository usuarioRepo;

    public List<UsuarioDTO> findAll() {
        // retorna uma lista de UsuarioDTO
        return usuarioRepo.findAll().stream()
                .map(obj -> new UsuarioDTO(obj))
                .collect(Collectors.toList());
    }

    public Usuario findById(Long id) {
        Optional<Usuario> obj = usuarioRepo.findById(id);
        return obj.orElse(null);
    }

    public Usuario findByEmail(String email) {
        Optional<Usuario> obj = usuarioRepo.findByEmail(email);
        return obj.orElseThrow(() -> new ObjectNotFoundException("Email não encontrado! Email: " + email));
    }

    public Usuario create(UsuarioDTO dto) {
        dto.setId(null);

        // Verifica se e-mail já existe
        if(usuarioRepo.findByEmail(dto.getEmail()).isPresent()) {
            throw new DataIntegrityViolationException("E-mail já cadastrado");
        }

        Usuario obj = new Usuario(dto);
        return usuarioRepo.save(obj);
    }
}
