package com.gestao.agricola.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.*;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.UUID;

@Entity
@Table(name = "cultivar")
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@EqualsAndHashCode
@ToString
public class Cultivar {

    @Id
    private UUID id;

    @Column(nullable = false, length = 30)
    private String identificacao;

    @ManyToOne
    @JoinColumn(name = "id_cultura")
    private Cultura cultura;

    @Column(nullable = false, name = "ciclo_em_dias")
    private int cicloEmDias;

    @Column(length = 255)
    private String observacoes;

    @Column(name = "data_cadastro")
    @JsonFormat(pattern = "dd/MM/yyyy")
    private LocalDate dataCadastro;

    @Column(name = "data_atualizacao")
    @JsonFormat(pattern = "dd/MM/yyyy")
    private LocalDate dataAtualizacao;
}
