package com.gestao.agricola.model;

import com.vladmihalcea.hibernate.type.json.JsonBinaryType;
import lombok.*;
import org.hibernate.annotations.Type;
import org.hibernate.annotations.TypeDef;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.UUID;

@Entity @Table(name = "propriedade")
@Getter @Setter
@NoArgsConstructor @AllArgsConstructor @Builder
@EqualsAndHashCode @ToString
@TypeDef(name = "jsonb", typeClass = JsonBinaryType.class)
public class Propriedade {

    @Id @GeneratedValue
    @Column(name = "id_propriedade")
    private UUID id;

    @Column(nullable = false, length = 150)
    private String nome;

    @ManyToOne
    @JoinColumn(name = "id_usuario")
    private Pessoa proprietario;

    @Column(nullable = false)
    private BigDecimal area;

    @OneToOne
    @JoinColumn(name = "id_unidade_de_medida")
    private UnidadeDeMedida unidadeDeMedida;

    @Type(type = "jsonb")
    @Column(columnDefinition = "jsonb")
    private String coordenadas;

}
