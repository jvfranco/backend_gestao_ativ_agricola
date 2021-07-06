package com.gestao.agricola.model.form;

import com.gestao.agricola.model.Perfil;
import com.gestao.agricola.model.Usuario;
import lombok.Getter;
import org.hibernate.validator.constraints.br.CPF;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

@Getter
public class UsuarioForm {

    @NotNull @NotEmpty
    private String nome;

    @NotNull @NotEmpty @CPF
    private String cpf;

    @NotNull @NotEmpty
    private String telefone;

    @NotNull @NotEmpty @Email
    private String email;

    @NotNull @NotEmpty
    private String perfil;

    @NotNull @NotEmpty
    private String usuario;

    @NotNull @NotEmpty
    private String senha;

    public Usuario converter() {
        Usuario usuario = Usuario.builder()
                .nome(this.nome)
                .cpf(this.cpf)
                .telefone(this.telefone)
                .email(this.email)
                .perfil(this.getPerfil().equalsIgnoreCase("administrador") ? Perfil.ADMINISTRADOR : Perfil.USUARIO)
                .usuario(this.usuario)
                .senha(this.senha)
                .build();
        return usuario;
    }
}
