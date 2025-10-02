package com.project.services;

import com.project.domains.Usuario;
import com.project.repositories.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

// Serviço de segurança que implementa UserDetailsService do Spring Security
@Service
public class SecurityService implements UserDetailsService {

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    // Método obrigatório do Spring Security para carregar usuário por username (email)
    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        // Busca usuário pelo email no banco
        Usuario usuario = usuarioRepository.findByEmail(email)
                .orElseThrow(() -> new UsernameNotFoundException("Usuário não encontrado: " + email));

        // Constrói UserDetails do Spring Security a partir da entidade Usuario
        return User.builder()
                .username(usuario.getEmail())      // Email como identificador
                .password(usuario.getSenha())      // Senha (já criptografada)
                .roles("USER")                     // Papel do usuário
                .build();
    }

    // Método auxiliar para criar usuário com senha já criptografada
    public Usuario criarUsuarioComSenhaCriptografada(String nome, String email, String senha) {
        Usuario usuario = new Usuario(nome, email, senha);
        usuario.setSenhaCriptografada(senha, passwordEncoder);
        return usuario;
    }
}