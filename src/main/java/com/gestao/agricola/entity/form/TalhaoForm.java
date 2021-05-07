package com.gestao.agricola.entity.form;

import com.gestao.agricola.entity.Talhao;
import com.gestao.agricola.entity.TipoSolo;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.UUID;

@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class TalhaoForm {

    private String id;

    private String identificacao;

    private BigDecimal area;

    private String coordenadas;

    private String tipoSolo;

    private String idPropriedade;


    public Talhao converter(TalhaoForm talhaoForm) {
        TipoSolo solo = null;
        if (talhaoForm.getTipoSolo().equalsIgnoreCase("ARENOSO")) {
            solo = TipoSolo.ARENOSO;
        } else if (talhaoForm.getTipoSolo().equalsIgnoreCase("ARGILOSO")) {
            solo = TipoSolo.ARGILOSO;
        } else {
            solo = TipoSolo.MISTO;
        }
        return Talhao.builder()
                .id(UUID.randomUUID())
                .identificacao(talhaoForm.getIdentificacao())
                .area(talhaoForm.getArea())
                .coordenadas(talhaoForm.getCoordenadas())
                .tipoSolo(solo)
                .dataCadastro(LocalDate.now())
                .build();
    }
}
