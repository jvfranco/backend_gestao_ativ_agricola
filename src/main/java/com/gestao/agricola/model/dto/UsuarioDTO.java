package com.gestao.agricola.model.dto;

import com.gestao.agricola.model.Usuario;
import lombok.Getter;

@Getter
public class UsuarioDTO {

    private String id;
    private String nome;
    private String cpf;
    private String email;
    private String telefone;
    private String usuario;
    private String perfil;

    public UsuarioDTO (Usuario usuario){
        this.id = usuario.getId().toString();
        this.nome = usuario.getNome();
        this.cpf = usuario.getCpf();
        this.email = usuario.getEmail();
        this.telefone = usuario.getTelefone();
        this.usuario = usuario.getUsuario();
        this.perfil = usuario.getPerfil().toString();
    }
}
