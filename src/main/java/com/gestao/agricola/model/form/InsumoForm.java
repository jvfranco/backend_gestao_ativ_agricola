package com.gestao.agricola.model.form;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;
import java.math.BigDecimal;
import java.util.UUID;

@AllArgsConstructor @NoArgsConstructor
@Getter
public class InsumoForm {

    private UUID id;

    @NotNull
    private String identificacao;

    @NotNull
    private String ingredientesAtivos;

    @NotNull
    private String aplicacao;

    @NotNull
    private String formulacao;

    @NotNull
    private String classeAgronomica;

    @NotNull
    private String modoDeAcao;

    @NotNull
    private BigDecimal quantidade;

    @NotNull
    private String idUnidadeDeMedida;

    @NotNull
    private  String idMarca;
}
