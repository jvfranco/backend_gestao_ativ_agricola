package com.gestao.agricola.model;

import lombok.*;
import org.hibernate.validator.constraints.br.CPF;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.util.UUID;

@Entity
@Table(name = "pessoa")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@EqualsAndHashCode
@ToString
public class Pessoa {

    @Id
    @GeneratedValue
    @Column(name = "id_pessoa")
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

    @Column(nullable = false)
    private String telefone;

    @Column(nullable = false)
    @Enumerated(value = EnumType.STRING)
    private Ocupacao ocupacao;
}
