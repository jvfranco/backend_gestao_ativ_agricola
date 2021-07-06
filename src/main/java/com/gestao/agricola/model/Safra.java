package com.gestao.agricola.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.*;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.UUID;

@Entity
@Table(name = "safra")
@Getter @Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@EqualsAndHashCode
@ToString
public class Safra {

    @Id @GeneratedValue
    @Column(name = "id_safra")
    private UUID id;

    @Column(nullable = false, length = 30)
    private String identificacao;

    @Column(name = "data_inicial")
    @JsonFormat(pattern = "yyyy-MM-dd")
    private LocalDate dataInicial;

    @Column(name = "data_final")
    @JsonFormat(pattern = "yyyy-MM-dd")
    private LocalDate dataFinal;

}
