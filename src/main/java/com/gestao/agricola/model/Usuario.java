package com.gestao.agricola.model;

import lombok.*;
import org.hibernate.validator.constraints.br.CPF;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
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

    @Id @GeneratedValue
    @Column(name = "id_usuario")
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
}
