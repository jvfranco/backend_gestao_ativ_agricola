package com.gestao.agricola.model.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

@Entity
@Table(name = "propriedade")
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@EqualsAndHashCode
@ToString
public class Propriedade {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(nullable = false, length = 150)
    @NotEmpty
    private String nome;

    @Column(nullable = false)
    @JoinColumn(name = "id_pessoa")
    private Pessoa proprietario;

    @Column(nullable = false)
    private BigDecimal area;

    @OneToMany
    private List<Coordenada> coordenadas;

    @OneToMany(mappedBy = "propriedade")
    private List<Talhao> talhoes;

    @Column(name = "data_cadastro")
    @JsonFormat(pattern = "dd/MM/yyyy")
    private LocalDate dataCadastro;

    @Column(name = "data_atualizacao")
    @JsonFormat(pattern = "dd/MM/yyyy")
    private LocalDate dataAtualizacao;
}
