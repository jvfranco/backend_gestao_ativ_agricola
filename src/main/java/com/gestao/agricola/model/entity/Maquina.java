package com.gestao.agricola.model.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.*;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
@Table(name = "maquina")
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@EqualsAndHashCode
@ToString
public class Maquina {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(nullable = false, length = 50)
    private String modelo;

    @ManyToOne
    @JoinColumn(name = "id_marca")
    private Marca marca;

    @Column(nullable = false)
    private String horimetro;

    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private Combustivel combustivel;

    @Column(nullable = false)
    private int potencia;

    @Column(name = "data_cadastro")
    @JsonFormat(pattern = "dd/MM/yyyy")
    private LocalDate dataCadastro;

    @Column(name = "data_atualizacao")
    @JsonFormat(pattern = "dd/MM/yyyy")
    private LocalDate dataAtualizacao;
}
