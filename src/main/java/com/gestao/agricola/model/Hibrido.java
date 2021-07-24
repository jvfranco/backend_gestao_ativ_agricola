package com.gestao.agricola.model;

import lombok.*;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.UUID;

@Entity
@Table(name = "hibrido")
@Getter @Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@EqualsAndHashCode
@ToString
public class Hibrido {

    @Id @GeneratedValue
    @Column(name = "id_hibrido")
    private UUID id;

    @Column(nullable = false, length = 30)
    private String identificacao;

    @ManyToOne
    @JoinColumn(name = "id_cultura")
    private Cultura cultura;

    @Column(nullable = false, name = "ciclo")
    private String ciclo;

    @Column(length = 255)
    private String observacoes;

    @ManyToOne
    @JoinColumn(name = "id_marca")
    private Marca marca;

    @Column(nullable = false)
    private BigDecimal quantidade;

    @OneToOne
    @JoinColumn(name = "id_unidade_de_medida")
    private UnidadeDeMedida unidadeDeMedida;

    public void atualizarQuantidade(BigDecimal quantidade) {
        if (quantidade.compareTo(this.quantidade) > 0) {
            throw new IllegalArgumentException("Valor excede a quantidade em estoque");
        }
        this.quantidade = this.quantidade.subtract(quantidade);
    }
}
