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
import java.time.LocalDate;
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

    public Optional<UsuarioDTO> update(UUID id, UsuarioForm usuarioForm) {
        return this.usuarioRepository.findById(id)
                .map(usuario -> {
                    Usuario usuarioSalvo = this.usuarioRepository.save(this.retornaUsuarioAposUpdate(usuarioForm, usuario));
                    return new UsuarioDTO(usuarioSalvo);
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

    private Usuario retornaUsuarioAposUpdate(UsuarioForm usuarioForm, Usuario usuario) {

        if(!usuarioForm.getNome().isEmpty() && usuarioForm.getNome() != null) {
            usuario.setNome(usuarioForm.getNome());
        }

        if(!usuarioForm.getCpf().isEmpty() && usuarioForm.getCpf() != null) {
            usuario.setCpf(usuarioForm.getCpf());
        }

        if(!usuarioForm.getTelefone().isEmpty() && usuarioForm.getTelefone() != null) {
            usuario.setTelefone(usuarioForm.getTelefone());
        }

        if(!usuarioForm.getEmail().isEmpty() && usuarioForm.getEmail() != null) {
            usuario.setEmail(usuarioForm.getEmail());
        }

        if(!usuarioForm.getUsuario().isEmpty() && usuarioForm.getUsuario() != null) {
            usuario.setUsuario(usuarioForm.getUsuario());
        }

        if(!usuarioForm.getPerfil().isEmpty() && usuarioForm.getPerfil() != null) {
            if(usuarioForm.getPerfil().equalsIgnoreCase(Perfil.ADMINISTRADOR.toString())) {
                usuario.setPerfil(Perfil.ADMINISTRADOR);
            } else {
                usuario.setPerfil(Perfil.USUARIO);
            }
        }

        usuario.setDataAtualizacao(LocalDate.now());

        return usuario;
    }
}
