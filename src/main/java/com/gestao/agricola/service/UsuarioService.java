package com.gestao.agricola.service;

import com.gestao.agricola.model.Perfil;
import com.gestao.agricola.model.Usuario;
import com.gestao.agricola.model.dto.UsuarioDTO;
import com.gestao.agricola.model.form.UsuarioForm;
import com.gestao.agricola.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;
import java.util.Optional;
import java.util.UUID;

@Service
public class UsuarioService {

    @Autowired
    private UsuarioRepository usuarioRepository;

    public Page<UsuarioDTO> findAll(Pageable paginacao) {
        return this.usuarioRepository.findAll(paginacao).map(UsuarioDTO::new);
    }

    public Optional<UsuarioDTO> findById(UUID id) {
        return this.usuarioRepository.findById(id).map(UsuarioDTO::new);
    }

    public URI save(Usuario usuario, UriComponentsBuilder uriBuilder) {
        this.usuarioRepository.save(usuario);
        return uriBuilder.path("/usuario/{id}").buildAndExpand(usuario.getId()).toUri();
    }

    public Optional<Usuario> update(UUID id, UsuarioForm usuarioForm) {
        return this.usuarioRepository.findById(id)
                .map(usuario -> {
                    usuario.setNome(usuarioForm.getNome());
                    usuario.setCpf(usuarioForm.getCpf());
                    usuario.setTelefone(usuarioForm.getTelefone());
                    usuario.setEmail(usuarioForm.getEmail());
                    usuario.setUsuario(usuarioForm.getUsuario());
                    usuario.setPerfil(usuarioForm.getPerfil().equalsIgnoreCase("administrador") ? Perfil.ADMINISTRADOR : Perfil.USUARIO);
                    return this.usuarioRepository.save(usuario);
                });
    }

    public boolean delete(UUID id) {
        this.usuarioRepository.findById(id)
                .map(usuario -> {
                    this.usuarioRepository.delete(usuario);
                    return true;
                });
        return false;
    }
}
