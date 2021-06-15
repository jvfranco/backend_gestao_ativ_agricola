package com.gestao.agricola.model.form;

import com.gestao.agricola.model.Propriedade;
import com.gestao.agricola.model.Usuario;
import com.gestao.agricola.repository.TalhaoRepository;
import com.gestao.agricola.repository.UsuarioRepository;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.EntityNotFoundException;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.UUID;

@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class PropriedadeForm {

    private String nome;

    private String idProprietario;

    private BigDecimal area;

    private String unidadeMedidaArea;

    private String coordenadas;

    public static Propriedade converter(PropriedadeForm propriedadeForm, UsuarioRepository usuarioRepository, TalhaoRepository talhaoRepository) {
        Usuario usuario = usuarioRepository.findById(UUID.fromString(propriedadeForm.getIdProprietario()))
                .orElseThrow(() -> new EntityNotFoundException("Usuário não encontrado"));

        return Propriedade.builder()
                .nome(propriedadeForm.getNome())
                .proprietario(usuario)
                .area(propriedadeForm.getArea())
                .unidadeMedidaArea(propriedadeForm.getUnidadeMedidaArea())
                .coordenadas(propriedadeForm.getCoordenadas())
                .dataCadastro(LocalDate.now())
                .build();
    }
}
