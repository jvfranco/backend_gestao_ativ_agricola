package com.gestao.agricola.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.*;

import javax.persistence.*;
import java.time.LocalDate;
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

    @ManyToOne
    @JoinColumn(name = "id_hibrido")
    private Hibrido hibrido;

    @Column(name = "data_cadastro")
    @JsonFormat(pattern = "dd/MM/yyyy")
    private LocalDate dataCadastro;

    @Column(name = "data_atualizacao")
    @JsonFormat(pattern = "dd/MM/yyyy")
    private LocalDate dataAtualizacao;
}
