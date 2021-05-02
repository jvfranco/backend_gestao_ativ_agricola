package com.gestao.agricola.entity.dto;

import com.gestao.agricola.entity.Propriedade;
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
