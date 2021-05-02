package com.gestao.agricola.entity.form;

import com.gestao.agricola.entity.Perfil;
import com.gestao.agricola.entity.Usuario;
import lombok.Getter;
import org.hibernate.validator.constraints.br.CPF;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;
import java.util.UUID;

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

    public static Usuario converter(UsuarioForm usuarioForm) {
        Usuario usuario = Usuario.builder()
                .id(UUID.randomUUID())
                .nome(usuarioForm.getNome())
                .cpf(usuarioForm.getCpf())
                .telefone(usuarioForm.getTelefone())
                .email(usuarioForm.getEmail())
                .perfil(usuarioForm.getPerfil().equalsIgnoreCase("administrador") ? Perfil.ADMINISTRADOR : Perfil.USUARIO)
                .usuario(usuarioForm.getUsuario())
                .senha(usuarioForm.getSenha())
                .dataCadastro(LocalDate.now())
                .build();
        return usuario;
    }
}
