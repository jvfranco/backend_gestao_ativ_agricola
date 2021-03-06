package com.gestao.agricola.model.form;

import lombok.Getter;

import javax.validation.constraints.NotNull;
import java.math.BigDecimal;

@Getter
public class HibridoForm {

    private String id;

    @NotNull
    private String identificacao;

    @NotNull
    private String idCultura;

    @NotNull
    private String ciclo;

    @NotNull
    private String idMarca;

    private String observacoes;

    @NotNull
    private BigDecimal quantidade;

    @NotNull
    private String idUnidadeDeMedida;

}
