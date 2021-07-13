package com.gestao.agricola.model;

import lombok.*;

import javax.persistence.*;
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

    @OneToOne
    @JoinColumn(name = "id_pessoa")
    private Pessoa pessoa;

    @NotNull
    @Column(nullable = true, unique = true)
    private String usuario;

    @Column(nullable = false)
    @NotNull
    private String senha;

    @Column(nullable = false)
    @Enumerated(value = EnumType.STRING)
    private Perfil perfil;
}
