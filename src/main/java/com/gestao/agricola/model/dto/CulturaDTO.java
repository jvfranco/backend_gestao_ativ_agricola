package com.gestao.agricola.model.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.UUID;

@AllArgsConstructor @NoArgsConstructor
@Getter @Builder
public class CulturaDTO {

    private UUID id;
    private String nome;

}
