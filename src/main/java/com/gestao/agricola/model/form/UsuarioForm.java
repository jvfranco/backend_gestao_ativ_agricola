package com.gestao.agricola.model.form;

import com.gestao.agricola.model.Perfil;
import com.gestao.agricola.model.Pessoa;
import com.gestao.agricola.model.Usuario;
import lombok.Getter;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

@Getter
public class UsuarioForm {

    @NotNull @NotEmpty
    private String perfil;

    @NotNull @NotEmpty
    private String usuario;

    @NotNull @NotEmpty
    private String senha;

    @NotNull @NotEmpty
    private Pessoa pessoa;

    public Usuario converter() {
        Usuario usuario = Usuario.builder()
                .perfil(this.getPerfil().equalsIgnoreCase("administrador") ? Perfil.ADMINISTRADOR : Perfil.USUARIO)
                .usuario(this.usuario)
                .senha(this.senha)
                .pessoa(this.pessoa)
                .build();
        return usuario;
    }
}
