package com.gestao.agricola.model;

import lombok.*;

import javax.persistence.*;
import java.util.UUID;

@Entity
@Table(name = "hibrido")
@Getter @Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@EqualsAndHashCode
@ToString
public class Hibrido {

    @Id @GeneratedValue
    @Column(name = "id_hibrido")
    private UUID id;

    @Column(nullable = false, length = 30)
    private String identificacao;

    @ManyToOne
    @JoinColumn(name = "id_cultura")
    private Cultura cultura;

    @Column(nullable = false, name = "ciclo")
    private String ciclo;

    @Column(length = 255)
    private String observacoes;

    @ManyToOne
    @JoinColumn(name = "id_marca")
    private Marca marca;

    @OneToOne
    @JoinColumn(name = "id_unidade_de_medida")
    private UnidadeDeMedida unidadeDeMedida;
}
