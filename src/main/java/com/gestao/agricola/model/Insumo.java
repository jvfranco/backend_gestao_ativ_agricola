package com.gestao.agricola.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.*;

import javax.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.UUID;

@Entity
@Table(name = "insumo")
@Getter @Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@EqualsAndHashCode
@ToString
public class Insumo {

    @Id @GeneratedValue
    private UUID id;

    private String identificacao;

    @Column(name = "ingredientes_ativos")
    private String ingredientesAtivos;

    @Column(nullable = false)
    private String aplicacao;

    @Column(nullable = false)
    private String formulacao;

    @Column(nullable = false, name = "classe_agronomica")
    private String classeAgronomica;

    @Column(nullable = false, name = "modo_de_acao")
    private String modoDeAcao;

    @Column(nullable = false)
    private BigDecimal quantidade;

    @OneToOne
    @JoinColumn(name = "id_unidade_de_medida")
    private UnidadeDeMedida unidadeDeMedida;

    @Column(name = "data_cadastro")
    @JsonFormat(pattern = "dd/MM/yyyy")
    private LocalDate dataCadastro;

    @Column(name = "data_atualizacao")
    @JsonFormat(pattern = "dd/MM/yyyy")
    private LocalDate dataAtualizacao;
}
