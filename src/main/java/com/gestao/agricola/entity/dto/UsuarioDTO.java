package com.gestao.agricola.entity.dto;

import com.gestao.agricola.entity.Usuario;
import lombok.Getter;

@Getter
public class UsuarioDTO {

    private String id;
    private String nome;
    private String usuario;
    private String perfil;

    public UsuarioDTO (Usuario usuario){
        this.id = usuario.getId().toString();
        this.nome = usuario.getNome();
        this.usuario = usuario.getUsuario();
        this.perfil = usuario.getPerfil().toString();
    }
}
