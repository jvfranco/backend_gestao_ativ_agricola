package com.gestao.agricola.model;

import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.Size;
import java.util.UUID;

@Entity
@Table(name = "unidade_de_medida")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@EqualsAndHashCode @ToString
public class UnidadeDeMedida {

    @Id @GeneratedValue
    @Column(name = "id_unidade_de_medida")
    private UUID id;

    @Column(name = "abreviacao", nullable = false)
    @Size(max = 3)
    private String abreviacao;

    @Column(name = "descricao", nullable = false)
    private String descricao;
}
