package com.gestao.agricola.model.form;

import lombok.Getter;

import javax.validation.constraints.NotNull;

@Getter
public class MaquinaForm {

    private String id;

    @NotNull
    private String modelo;

    @NotNull
    private String idMarca;

    @NotNull
    private String horimetro;

    @NotNull
    private String combustivel;

    @NotNull
    private String potencia;
}
