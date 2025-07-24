package com.project.services;

import com.project.domains.dtos.UsuarioDTO;
import com.project.repositories.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
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
}
