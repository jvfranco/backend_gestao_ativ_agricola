package com.gestao.agricola.model.form;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.gestao.agricola.model.AptAtividadeAgricola;
import com.gestao.agricola.service.PropriedadeService;
import com.gestao.agricola.service.SafraService;
import com.gestao.agricola.service.TalhaoService;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.UUID;

@AllArgsConstructor
@NoArgsConstructor
@Getter
public class AptAtividadeAgricolaForm {

    @NotNull
    private String descricaoAtividade;

    @NotNull
    private String safra;

    @NotNull
    private String propriedade;

    @NotNull
    private String talhao;

    @NotNull
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd/MM/yyyy")
    private LocalDate dataAtividade;

    @NotNull
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "HH:mm")
    private LocalTime horaInicio;

    @NotNull
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "HH:mm")
    private LocalTime horaTermino;

    public AptAtividadeAgricola converte(PropriedadeService propriedadeService, TalhaoService talhaoService, SafraService safraService) {
        return AptAtividadeAgricola.builder()
                .descricaoAtividade(this.descricaoAtividade)
                .dataAtividade(this.dataAtividade)
                .horaInicio(this.horaInicio)
                .horaTermino(this.horaInicio)
                .propriedade(propriedadeService.findById(UUID.fromString(this.propriedade)))
                .talhao(talhaoService.findById(UUID.fromString(this.talhao)))
                .safra(safraService.findById(UUID.fromString(this.safra)))
                .build();
    }
}
