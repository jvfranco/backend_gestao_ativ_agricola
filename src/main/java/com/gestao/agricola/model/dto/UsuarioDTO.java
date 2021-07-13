package com.gestao.agricola.model.dto;

import com.gestao.agricola.model.Pessoa;
import com.gestao.agricola.model.Usuario;
import lombok.Getter;

@Getter
public class UsuarioDTO {

    private String id;
    private String usuario;
    private String perfil;
    private Pessoa pessoa;

    public UsuarioDTO (Usuario usuario){
        this.id = usuario.getId().toString();
        this.usuario = usuario.getUsuario();
        this.perfil = usuario.getPerfil().toString();
        this.pessoa = usuario.getPessoa();
    }
}
