package com.gestao.agricola.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.*;

import javax.persistence.*;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.UUID;

@Entity
@Table(name = "atividade_agricola")
@Getter @Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@EqualsAndHashCode
@ToString
public class AptAtividadeAgricola {

    @Id @GeneratedValue
    @Column(name = "id_apontamento_cabecalho")
    private UUID id;

    @Column(nullable = false, length = 255, name = "descricao_atividade")
    private String descricaoAtividade;

    @ManyToOne
    @JoinColumn(name = "id_safra")
    private Safra safra;

    @ManyToOne
    @JoinColumn(name = "id_propriedade")
    private Propriedade propriedade;

    @ManyToOne
    @JoinColumn(name = "id_talhao")
    private Talhao talhao;

    @Column(name = "data_atividade")
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd/MM/yyyy")
    private LocalDate dataAtividade;

    @Column(name = "hora_inicio")
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "HH:mm")
    private LocalTime horaInicio;

    @Column(name = "hora_termino")
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "HH:mm")
    private LocalTime horaTermino;
}
