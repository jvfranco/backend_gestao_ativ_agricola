package com.gestao.agricola.model;

import lombok.*;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.UUID;

@Entity
@Table(name = "atividade_agricola_detalhe")
@Getter @Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@EqualsAndHashCode
@ToString
public class AptAtividadeAgricolaDetalhe {

    @Id
    @GeneratedValue
    @Column(name = "id_apontamento_detalhe")
    private UUID id;

    @ManyToOne
    @JoinColumn(name = "id_apontamento_cabecalho")
    private AptAtividadeAgricola idApontamentoCabecalho;

    @ManyToOne
    @JoinColumn(name = "id_maquina")
    private Maquina maquina;

    @ManyToOne
    @JoinColumn(name = "id_insumo")
    private Insumo insumo;

    @Column(name = "qnte_insumo")
    private BigDecimal quantidadeInsumo;

    @ManyToOne
    @JoinColumn(name = "id_hibrido")
    private Hibrido hibrido;

    @Column(name = "qtde_hibrido")
    private BigDecimal quantidadeHibrido;

    @ManyToOne
    @JoinColumn(name = "id_pessoa")
    private Pessoa funcionario;
}
