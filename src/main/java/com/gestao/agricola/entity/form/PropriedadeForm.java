package com.gestao.agricola.entity.form;

import com.gestao.agricola.entity.Propriedade;
import com.gestao.agricola.entity.Talhao;
import com.gestao.agricola.entity.Usuario;
import com.gestao.agricola.repository.TalhaoRepository;
import com.gestao.agricola.repository.UsuarioRepository;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.EntityNotFoundException;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class PropriedadeForm {

    private String id;

    private String nome;

    private String idProprietario;

    private BigDecimal area;

    private String coordenadas;

    private List<String> idTalhoes;

    public static Propriedade converter(PropriedadeForm propriedadeForm, UsuarioRepository usuarioRepository, TalhaoRepository talhaoRepository) {
        Usuario usuario = usuarioRepository.findById(UUID.fromString(propriedadeForm.getIdProprietario()))
                .orElseThrow(() -> new EntityNotFoundException("Usuário não encontrado"));

        List<Talhao> talhoes = propriedadeForm.getIdTalhoes()
                .stream()
                .map(id -> {
                    return talhaoRepository.findById(UUID.fromString(id)).get();
                }).collect(Collectors.toList());

        return Propriedade.builder()
                .id(UUID.randomUUID())
                .nome(propriedadeForm.getNome())
                .proprietario(usuario)
                .area(propriedadeForm.getArea())
                .talhoes(talhoes)
                .dataCadastro(LocalDate.now())
                .build();
    }
}
