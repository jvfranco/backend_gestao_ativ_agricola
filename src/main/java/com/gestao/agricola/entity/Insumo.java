package com.gestao.agricola.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.*;

import javax.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

@Entity
@Table(name = "insumo")
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@EqualsAndHashCode
@ToString
public class Insumo {

    @Id
    private UUID id;

    private String identificacao;

    @OneToMany
    private List<IngredienteAtivo> ingredientesAtivos;

    @Column(nullable = false)
    private String aplicacao;

    @Column(nullable = false)
    private String formulacao;

    @Column(nullable = false, name = "classe_agronomica")
    private String classeAgronomica;

    @Column(nullable = false, name = "modo_de_acao")
    private String modoDeAcao;

    @Column
    private String embalagem;

    @Column(nullable = false)
    private BigDecimal quantidade;

    @Column(nullable = false, name = "unidade_de_medida")
    private String unidadeDeMedida;

    @Column(name = "data_cadastro")
    @JsonFormat(pattern = "dd/MM/yyyy")
    private LocalDate dataCadastro;

    @Column(name = "data_atualizacao")
    @JsonFormat(pattern = "dd/MM/yyyy")
    private LocalDate dataAtualizacao;
}
