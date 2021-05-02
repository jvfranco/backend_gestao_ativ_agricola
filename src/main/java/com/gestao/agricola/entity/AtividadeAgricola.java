package com.gestao.agricola.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.*;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

@Entity
@Table(name = "atividade_agricola")
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@EqualsAndHashCode
@ToString
public class AtividadeAgricola {

    @Id
    private UUID id;

    @Column(nullable = false, length = 255, name = "descricao_atividade")
    private String descricaoAtividade;

    @ManyToOne
    @JoinColumn(name = "id_safra")
    private Safra safra;

    @ManyToMany
    @JoinTable(name="atividades_has_maquinas",
            joinColumns = {@JoinColumn(name="id_atividade_agricola")},
            inverseJoinColumns = {@JoinColumn(name="id_maquina")})
    private List<Maquina> maquinas;

    @ManyToMany
    @JoinTable(name="atividades_has_insumos",
            joinColumns = {@JoinColumn(name="id_atividade_agricola")},
            inverseJoinColumns = {@JoinColumn(name="id_insumo")})
    private List<Insumo> insumos;

    @ManyToOne
    @JoinColumn(name = "id_talhao")
    private Talhao talhao;

    @Column(name = "data_atividade")
    @JsonFormat(pattern = "dd/MM/yyyy")
    private LocalDate dataAtividade;

    @Column(name = "data_cadastro")
    @JsonFormat(pattern = "dd/MM/yyyy")
    private LocalDate dataCadastro;

    @Column(name = "data_atualizacao")
    @JsonFormat(pattern = "dd/MM/yyyy")
    private LocalDate dataAtualizacao;
}
