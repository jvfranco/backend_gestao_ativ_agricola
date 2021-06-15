package com.gestao.agricola.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.*;

import javax.persistence.*;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.UUID;

@Entity
@Table(name = "ocorrencia")
@Getter @Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@EqualsAndHashCode
@ToString
public class Ocorrencia {

    @Id @GeneratedValue
    private UUID id;

    @Column(nullable = false, length = 100)
    private String titulo;

    @Column(nullable = false, length = 255)
    private String observacoes;

    @ManyToOne
    @JoinColumn(name = "id_safra")
    private Safra safra;

    @ManyToOne
    @JoinColumn(name = "id_cultivar")
    private Cultivar cultivar;

    @ManyToOne
    @JoinColumn(name = "id_talhao")
    private Talhao talhao;

    @Column(name = "data_ocorrencia")
    @JsonFormat(pattern = "dd/MM/yyyy hh:mm:ss")
    private LocalDateTime dataOcorrencia;

    @Column(name = "data_cadastro")
    @JsonFormat(pattern = "dd/MM/yyyy")
    private LocalDate dataCadastro;

    @Column(name = "data_atualizacao")
    @JsonFormat(pattern = "dd/MM/yyyy")
    private LocalDate dataAtualizacao;
}
