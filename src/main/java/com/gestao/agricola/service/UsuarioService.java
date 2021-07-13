package com.gestao.agricola.service;

import com.gestao.agricola.model.Perfil;
import com.gestao.agricola.model.Pessoa;
import com.gestao.agricola.model.Usuario;
import com.gestao.agricola.model.dto.UsuarioDTO;
import com.gestao.agricola.model.form.UsuarioForm;
import com.gestao.agricola.repository.PessoaRepository;
import com.gestao.agricola.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.web.util.UriComponentsBuilder;

import javax.persistence.EntityNotFoundException;
import java.net.URI;
import java.util.Optional;
import java.util.UUID;

@Service
public class UsuarioService {

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Autowired
    private PessoaRepository pessoaRepository;

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

    public void delete(UUID id) {
        try {
            this.usuarioRepository.deleteById(id);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    private Usuario retornaUsuarioAposUpdate(UsuarioForm usuarioForm, Usuario usuario) {

        if (usuarioForm.getPessoa() != null && usuarioForm.getPessoa() != usuario.getPessoa()) {
            Pessoa pessoa = pessoaRepository.findById(usuarioForm.getPessoa().getId())
                    .orElseThrow(() -> new EntityNotFoundException("Pessoa não encontrada"));
            usuario.setPessoa(pessoa);
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

        return usuario;
    }
}
