package com.gestao.agricola.model.form;

import com.gestao.agricola.model.Propriedade;
import com.gestao.agricola.model.Talhao;
import com.gestao.agricola.model.TipoSolo;
import com.gestao.agricola.model.UnidadeDeMedida;
import com.gestao.agricola.repository.PropriedadeRepository;
import com.gestao.agricola.repository.UnidadeDeMedidaRepository;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.EntityNotFoundException;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.UUID;

@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class TalhaoForm {

    @NotNull
    private String identificacao;

    @NotNull
    private BigDecimal area;

    @NotNull
    private String coordenadas;

    @NotNull
    private String idUnidadeDeMedida;

    @NotNull
    private String tipoSolo;

    @NotNull
    private String idPropriedade;


    public Talhao converter(PropriedadeRepository propriedadeRepository, UnidadeDeMedidaRepository unidadeDeMedidaRepository) {
        TipoSolo solo = null;
        UnidadeDeMedida unidadeDeMedida = null;
        Propriedade propriedade = null;

        if (this.tipoSolo.equalsIgnoreCase("ARENOSO")) {
            solo = TipoSolo.ARENOSO;
        } else if (this.tipoSolo.equalsIgnoreCase("ARGILOSO")) {
            solo = TipoSolo.ARGILOSO;
        } else {
            solo = TipoSolo.MISTO;
        }

        if(!this.idPropriedade.isEmpty() && this.idPropriedade != null) {
            propriedade = propriedadeRepository.findById(UUID.fromString(this.idPropriedade))
                    .orElseThrow(() -> new EntityNotFoundException("Propriedade de id: " + this.idPropriedade + " não encontrada!"));
        }

        if(!this.idUnidadeDeMedida.isEmpty() && this.idUnidadeDeMedida != null) {
            unidadeDeMedida = unidadeDeMedidaRepository.findById(UUID.fromString(this.idUnidadeDeMedida))
                    .orElseThrow(() -> new EntityNotFoundException("Unidade de medidade de id: " + this.idUnidadeDeMedida + " não encontrada"));
        }

        return Talhao.builder()
                .identificacao(this.identificacao)
                .area(this.area)
                .coordenadas(this.coordenadas)
                .unidadeDeMedida(unidadeDeMedida)
                .tipoSolo(solo)
                .propriedade(propriedade)
                .dataCadastro(LocalDate.now())
                .build();
    }
}
