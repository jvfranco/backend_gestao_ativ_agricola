package com.gestao.agricola.model;

import lombok.*;
import org.springframework.security.core.GrantedAuthority;

import javax.persistence.*;

@Entity
@Table(name = "perfil")
@Getter
@Setter
@NoArgsConstructor @AllArgsConstructor @Builder
public class Perfil implements GrantedAuthority {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nome;

    public Perfil(String nome) {
        this.nome = nome;
    }

    @Override
    public String getAuthority() {
        return this.nome;
    }
}
