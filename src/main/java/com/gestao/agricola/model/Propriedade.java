package com.gestao.agricola.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.vladmihalcea.hibernate.type.json.JsonBinaryType;
import lombok.*;
import org.hibernate.annotations.Type;
import org.hibernate.annotations.TypeDef;

import javax.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.UUID;

@Entity @Table(name = "propriedade")
@Getter @Setter
@NoArgsConstructor @AllArgsConstructor @Builder
@EqualsAndHashCode @ToString
@TypeDef(name = "jsonb", typeClass = JsonBinaryType.class)
public class Propriedade {

    @Id @GeneratedValue
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

    @Column(name = "data_cadastro")
    @JsonFormat(pattern = "dd/MM/yyyy")
    private LocalDate dataCadastro;

    @Column(name = "data_atualizacao")
    @JsonFormat(pattern = "dd/MM/yyyy")
    private LocalDate dataAtualizacao;

}
