package com.gestao.agricola.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.*;
import org.hibernate.annotations.Type;
import org.hibernate.annotations.TypeDef;
import com.vladmihalcea.hibernate.type.json.JsonBinaryType;

import javax.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.UUID;

@Entity
@Table(name = "talhao")
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@EqualsAndHashCode
@ToString
@TypeDef(name = "jsonb", typeClass = JsonBinaryType.class)
public class Talhao {

    @Id
    private UUID id;

    @Column(nullable = false)
    private String identificacao;

    @Column(nullable = false)
    private BigDecimal area;

    @Type(type = "jsonb")
    @Column(nullable = false, columnDefinition = "jsonb")
    private String coordenadas;

    @Enumerated(EnumType.STRING)
    private TipoSolo tipoSolo;

    @ManyToOne
    @JoinColumn(name = "id_propriedade")
    private Propriedade propriedade;

    @Column(name = "data_cadastro")
    @JsonFormat(pattern = "dd/MM/yyyy")
    private LocalDate dataCadastro;

    @Column(name = "data_atualizacao")
    @JsonFormat(pattern = "dd/MM/yyyy")
    private LocalDate dataAtualizacao;
}
