package com.gestao.agricola.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.vladmihalcea.hibernate.type.json.JsonBinaryType;
import lombok.*;
import org.hibernate.annotations.Type;
import org.hibernate.annotations.TypeDef;

import javax.persistence.*;
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
@TypeDef(name = "jsonb", typeClass = JsonBinaryType.class)
public class Ocorrencia {

    @Id @GeneratedValue
    @Column(name = "id_ocorrencia")
    private UUID id;

    @Column(nullable = false, length = 100)
    private String titulo;

    @Column(nullable = false, length = 255)
    private String descricao;

    @ManyToOne
    @JoinColumn(name = "id_safra")
    private Safra safra;

    @ManyToOne
    @JoinColumn(name = "id_propriedade")
    private Propriedade propriedade;

    @ManyToOne
    @JoinColumn(name = "id_talhao")
    private Talhao talhao;

    @Type(type = "jsonb")
    @Column(nullable = false, columnDefinition = "jsonb")
    private String coordenadas;

    @Column(name = "data_ocorrencia")
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd/MM/yyyy HH:mm")
    private LocalDateTime dataOcorrencia;
}
