package com.gestao.agricola.model.form;

import com.gestao.agricola.model.Pessoa;
import com.gestao.agricola.model.Propriedade;
import com.gestao.agricola.model.UnidadeDeMedida;
import com.gestao.agricola.repository.PessoaRepository;
import com.gestao.agricola.repository.UnidadeDeMedidaRepository;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.EntityNotFoundException;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;
import java.util.UUID;

@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class PropriedadeForm {

    @NotNull
    private String nome;

    @NotNull
    private String idProprietario;

    @NotNull
    private BigDecimal area;

    @NotNull
    private String idUnidade;

    @NotNull
    private String coordenadas;

    public static Propriedade converter(PropriedadeForm propriedadeForm, PessoaRepository pessoaRepository, UnidadeDeMedidaRepository unidadeDeMedidaRepository) {
        Pessoa proprietario = pessoaRepository.findById(UUID.fromString(propriedadeForm.getIdProprietario()))
                .orElseThrow(() -> new EntityNotFoundException("Pessoa não encontrada"));

        UnidadeDeMedida unidadeDeMedida = unidadeDeMedidaRepository.findById(UUID.fromString(propriedadeForm.getIdUnidade()))
                .orElseThrow(() -> new EntityNotFoundException("Unidade de Medida não encontrada"));

        return Propriedade.builder()
                .nome(propriedadeForm.getNome())
                .proprietario(proprietario)
                .area(propriedadeForm.getArea())
                .unidadeDeMedida(unidadeDeMedida)
                .coordenadas(propriedadeForm.getCoordenadas())
                .build();
    }
}
