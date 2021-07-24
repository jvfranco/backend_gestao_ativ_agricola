package com.gestao.agricola.model.form;

import com.gestao.agricola.model.AptAtividadeAgricolaDetalhe;
import com.gestao.agricola.model.Hibrido;
import com.gestao.agricola.model.Insumo;
import com.gestao.agricola.service.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.apache.logging.log4j.util.Strings;

import javax.validation.constraints.NotNull;
import java.math.BigDecimal;
import java.util.Objects;
import java.util.UUID;

@AllArgsConstructor
@NoArgsConstructor
@Getter
public class AptAtividadeAgricolaDetalheForm {

    @NotNull
    private String idApontamentoCabecalho;

    @NotNull
    private String maquina;

    private String insumo;

    private BigDecimal quantidadeInsumo;

    private String hibrido;

    private BigDecimal quantidadeHibrido;

    @NotNull
    private String funcionario;

    public AptAtividadeAgricolaDetalhe converte(AptAtividadeAgricolaService aptAtividadeAgricolaService,
                                                MaquinaService maquinaService, InsumoService insumoService,
                                                HibridoService hibridoService, PessoaService pessoaService) {
        Insumo insumo = null;
        Hibrido hibrido = null;

        if (Objects.nonNull(this.insumo) && Strings.isNotEmpty(this.insumo)) {
            insumo = insumoService.findById(UUID.fromString(this.insumo));
            insumo.atualizarQuantidade(this.quantidadeInsumo);
            insumoService.update(insumo.getId(), insumo);
        }

        if (Objects.nonNull(this.hibrido) && Strings.isNotEmpty(this.hibrido)) {
            hibrido = hibridoService.findById(UUID.fromString(this.hibrido));
            hibrido.atualizarQuantidade(this.quantidadeHibrido);
            hibridoService.update(hibrido.getId(), hibrido);
        }

        return AptAtividadeAgricolaDetalhe.builder()
                .idApontamentoCabecalho(aptAtividadeAgricolaService.findById(UUID.fromString(this.idApontamentoCabecalho)))
                .maquina(maquinaService.findById(UUID.fromString(this.maquina)))
                .insumo(insumo)
                .quantidadeInsumo(this.quantidadeInsumo)
                .hibrido(hibrido)
                .quantidadeHibrido(this.quantidadeHibrido)
                .funcionario(pessoaService.findById(UUID.fromString(this.funcionario)))
                .build();
    }
}
