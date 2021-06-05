package com.gestao.agricola.model.dto;

import com.gestao.agricola.model.Talhao;
import lombok.Getter;

import java.math.BigDecimal;

@Getter
public class TalhaoDTO {

    private String id;

    private String identificacao;

    private BigDecimal area;

    private String coordenadas;

    private String tipoSolo;

    public TalhaoDTO(Talhao talhao) {
        this.id = talhao.getId().toString();
        this.identificacao = talhao.getIdentificacao();
        this.area = talhao.getArea();
        this.coordenadas = talhao.getCoordenadas();
        this.tipoSolo = talhao.getTipoSolo().toString();
    }

}
