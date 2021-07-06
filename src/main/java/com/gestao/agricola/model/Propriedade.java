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
    private Usuario proprietario;

    @Column(nullable = false)
    private BigDecimal area;

    @Column(nullable = false)
    private String unidadeMedidaArea;

    @Type(type = "jsonb")
    @Column(columnDefinition = "jsonb")
    private String coordenadas;

}
