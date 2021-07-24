package com.gestao.agricola.model.form;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.gestao.agricola.model.Ocorrencia;
import com.gestao.agricola.model.Propriedade;
import com.gestao.agricola.model.Safra;
import com.gestao.agricola.model.Talhao;
import com.gestao.agricola.service.PropriedadeService;
import com.gestao.agricola.service.SafraService;
import com.gestao.agricola.service.TalhaoService;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.apache.logging.log4j.util.Strings;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.time.LocalDateTime;
import java.util.Objects;
import java.util.UUID;

@AllArgsConstructor @NoArgsConstructor @Getter
public class OcorrenciaForm {

    @NotNull @Size(max = 100)
    private String titulo;

    @NotNull @Size(max = 255)
    private String descricao;

    @NotNull
    private String safra;

    @NotNull
    private String propriedade;

    @NotNull
    private String talhao;

    @NotNull
    private String coordenadas;

    @NotNull
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd/MM/yyyy HH:mm")
    private LocalDateTime dataOcorrencia;

    public Ocorrencia converte(PropriedadeService propriedadeService, TalhaoService talhaoService, SafraService safraService) {

        Propriedade propriedade = null;
        Talhao talhao = null;
        Safra safra = null;

        if (Objects.nonNull(this.propriedade) && Strings.isNotEmpty(this.propriedade)) {
            propriedade = propriedadeService.findById(UUID.fromString(this.propriedade));
        }

        if (Objects.nonNull(this.talhao) && Strings.isNotEmpty(this.talhao)) {
            talhao = talhaoService.findById(UUID.fromString(this.talhao));
        }

        if (Objects.nonNull(this.safra) && Strings.isNotEmpty(this.safra)) {
            safra = safraService.findById(UUID.fromString(this.safra));
        }

        return Ocorrencia.builder()
                .titulo(this.titulo)
                .descricao(this.descricao)
                .safra(safra)
                .propriedade(propriedade)
                .talhao(talhao)
                .coordenadas(this.coordenadas)
                .dataOcorrencia(this.dataOcorrencia)
                .build();
    }
}
