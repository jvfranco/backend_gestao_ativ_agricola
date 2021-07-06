package com.gestao.agricola.model;

import lombok.*;

import javax.persistence.*;
import java.util.UUID;

@Entity
@Table(name = "marca")
@Getter @Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@EqualsAndHashCode
@ToString
public class Marca {

    @Id @GeneratedValue
    @Column(name = "id_marca")
    private UUID id;

    @Column(nullable = false, length = 30)
    private String nome;
}
