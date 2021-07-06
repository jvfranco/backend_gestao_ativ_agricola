package com.gestao.agricola.model;

import lombok.*;

import javax.persistence.*;
import java.util.UUID;

@Entity
@Table(name = "maquina")
@Getter @Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@EqualsAndHashCode
@ToString
public class Maquina {

    @Id @GeneratedValue
    @Column(name = "id_maquina")
    private UUID id;

    @Column(nullable = false, length = 50)
    private String modelo;

    @ManyToOne
    @JoinColumn(name = "id_marca")
    private Marca marca;

    @Column(nullable = false)
    private String horimetro;

    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private Combustivel combustivel;

    @Column(nullable = false)
    private int potencia;
}
