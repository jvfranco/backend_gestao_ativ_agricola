package com.gestao.agricola.model;

import com.vladmihalcea.hibernate.type.json.JsonBinaryType;
import lombok.*;
import org.hibernate.annotations.Type;
import org.hibernate.annotations.TypeDef;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.UUID;

@Entity @Table(name = "talhao")
@Getter @Setter
@NoArgsConstructor
@AllArgsConstructor @Builder
@EqualsAndHashCode @ToString
@TypeDef(name = "jsonb", typeClass = JsonBinaryType.class)
public class Talhao {

    @Id @GeneratedValue
    @Column(name = "id_talhao")
    private UUID id;

    @Column(nullable = false)
    private String identificacao;

    @Column(nullable = false)
    private BigDecimal area;

    @Type(type = "jsonb")
    @Column(nullable = false, columnDefinition = "jsonb")
    private String coordenadas;

    @OneToOne
    @JoinColumn(name = "id_unidade_de_medida")
    private UnidadeDeMedida unidadeDeMedida;

    @Enumerated(EnumType.STRING)
    private TipoSolo tipoSolo;

    @ManyToOne
    @JoinColumn(name = "id_propriedade")
    private Propriedade propriedade;

}
