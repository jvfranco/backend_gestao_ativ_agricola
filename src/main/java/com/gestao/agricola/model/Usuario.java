package com.gestao.agricola.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.gestao.agricola.model.form.UsuarioForm;
import lombok.*;
import org.hibernate.validator.constraints.br.CPF;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;
import java.util.UUID;

@Entity
@Table(name = "usuario")
@Getter @Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@EqualsAndHashCode
@ToString
public class Usuario {

    @Id
    private UUID id;

    @Column(nullable = false, length = 150)
    @NotEmpty
    private String nome;

    @Column(nullable = false, length = 11, unique = true)
    @NotNull @CPF
    private String cpf;

    @Column(nullable = false, length = 150)
    @Email
    private String email;

    @Column
    private String telefone;

    @Column(nullable = true)
    @NotNull
    private String usuario;

    @Column(nullable = false)
    @Enumerated(value = EnumType.STRING)
    private Perfil perfil;

    @Column(nullable = false)
    @NotNull
    private String senha;

    @Column(name = "data_cadastro")
    @JsonFormat(pattern = "dd/MM/yyyy")
    private LocalDate dataCadastro;

    @Column(name = "data_atualizacao")
    @JsonFormat(pattern = "dd/MM/yyyy")
    private LocalDate dataAtualizacao;

    public static Usuario retornaUsuarioAposUpdate(UsuarioForm usuarioForm, Usuario usuario) {

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
