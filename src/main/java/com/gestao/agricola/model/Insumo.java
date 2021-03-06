package com.gestao.agricola.model;

import lombok.*;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.UUID;

@Entity
@Table(name = "insumo")
@Getter @Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@EqualsAndHashCode
@ToString
public class Insumo {

    @Id @GeneratedValue
    @Column(name = "id_insumo")
    private UUID id;

    private String identificacao;

    @Column(name = "ingredientes_ativos")
    private String ingredientesAtivos;

    @Column(nullable = false)
    private String aplicacao;

    @Column(nullable = false)
    private String formulacao;

    @Column(nullable = false, name = "classe_agronomica")
    private String classeAgronomica;

    @Column(nullable = false, name = "modo_de_acao")
    private String modoDeAcao;

    @Column(nullable = false)
    private BigDecimal quantidade;

    @OneToOne
    @JoinColumn(name = "id_unidade_de_medida")
    private UnidadeDeMedida unidadeDeMedida;

    @ManyToOne
    @JoinColumn(name = "id_marca")
    private Marca marca;

    public void atualizarQuantidade(BigDecimal quantidade) {
        if (quantidade.compareTo(this.quantidade) > 0) {
            throw new IllegalArgumentException("Valor excede a quantidade em estoque");
        }
        this.quantidade = this.quantidade.subtract(quantidade);
    }

}
