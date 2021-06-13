package com.gestao.agricola.service;

import com.gestao.agricola.model.Propriedade;
import com.gestao.agricola.model.Talhao;
import com.gestao.agricola.model.UnidadeDeMedida;
import com.gestao.agricola.model.dto.TalhaoDTO;
import com.gestao.agricola.model.form.TalhaoForm;
import com.gestao.agricola.repository.PropriedadeRepository;
import com.gestao.agricola.repository.TalhaoRepository;
import com.gestao.agricola.repository.UnidadeDeMedidaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.web.util.UriComponentsBuilder;

import javax.persistence.EntityNotFoundException;
import java.math.BigDecimal;
import java.net.URI;
import java.time.LocalDate;
import java.util.Optional;
import java.util.UUID;

@Service
public class TalhaoService {

    @Autowired
    private TalhaoRepository talhaoRepository;

    @Autowired
    private PropriedadeRepository propriedadeRepository;

    @Autowired
    private UnidadeDeMedidaRepository unidadeDeMedidaRepository;

    public Page<TalhaoDTO> findAll(Pageable paginacao) {
        return this.talhaoRepository.findAll(paginacao).map(TalhaoDTO::new);
    }

    public Optional<TalhaoDTO> findById(UUID id) {
        return this.talhaoRepository.findById(id).map(TalhaoDTO::new);
    }

    public URI save(Talhao talhao, UriComponentsBuilder uriBuilder) {
        this.talhaoRepository.save(talhao);
        return uriBuilder.path("/talhao/{id}").buildAndExpand(talhao.getId()).toUri();
    }

    public Optional<Talhao> update(UUID id, TalhaoForm talhaoForm) {
        return this.talhaoRepository.findById(id)
                .map(talhao -> {
                    Talhao talhaoAtualizado = this.talhaoRepository.save(this.retornaTalhaoAposAtualizacao(talhaoForm, talhao));
                    return talhaoAtualizado;
                });
    }

    public void delete(UUID id) {
        try {
            this.talhaoRepository.deleteById(id);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    private Talhao retornaTalhaoAposAtualizacao(TalhaoForm talhaoForm, Talhao talhao) {

        if(!talhaoForm.getIdPropriedade().isEmpty() && talhaoForm.getIdPropriedade() != null) {
            Propriedade propriedade = this.propriedadeRepository.findById(UUID.fromString(talhaoForm.getIdPropriedade()))
                    .orElseThrow(() -> new EntityNotFoundException("Propriedade de id: " + talhaoForm.getIdPropriedade() + " não encontrada!"));
            talhao.setPropriedade(propriedade);
        }

        if(!talhaoForm.getIdentificacao().isEmpty() && talhaoForm.getIdentificacao() != null) {
            talhao.setIdentificacao(talhaoForm.getIdentificacao());
        }

        if(talhaoForm.getArea().compareTo(BigDecimal.ZERO) > 0) {
            talhao.setArea(talhaoForm.getArea());
        }

        if(!talhaoForm.getCoordenadas().isEmpty() && talhaoForm.getCoordenadas() != null) {
            talhao.setCoordenadas(talhaoForm.getCoordenadas());
        }

        if(!talhaoForm.getIdUnidadeDeMedida().isEmpty() && talhaoForm.getIdUnidadeDeMedida() != null) {
            UnidadeDeMedida unidadeDeMedida = this.unidadeDeMedidaRepository.findById(UUID.fromString(talhaoForm.getIdUnidadeDeMedida()))
                    .orElseThrow(() -> new EntityNotFoundException("Unidade de medidade de id: " + talhaoForm.getIdUnidadeDeMedida() + " não encontrada"));
            talhao.setUnidadeDeMedida(unidadeDeMedida);
        }

        talhao.setDataAtualizacao(LocalDate.now());

        return talhao;

    }
}
