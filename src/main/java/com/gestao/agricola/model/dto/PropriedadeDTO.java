package com.gestao.agricola.model.dto;

import com.gestao.agricola.model.Propriedade;
import lombok.Getter;

@Getter
public class PropriedadeDTO {

    private String id;
    private String nome;
    private String nomeProprietario;

    public PropriedadeDTO (Propriedade propriedade){
        this.id = propriedade.getId().toString();
        this.nome = propriedade.getNome();
        this.nomeProprietario = propriedade.getProprietario().getNome();
    }
}
