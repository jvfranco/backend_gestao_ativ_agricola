package com.gestao.agricola.service;

import com.gestao.agricola.model.Propriedade;
import com.gestao.agricola.model.Talhao;
import com.gestao.agricola.model.UnidadeDeMedida;
import com.gestao.agricola.repository.PropriedadeRepository;
import com.gestao.agricola.repository.TalhaoRepository;
import com.gestao.agricola.repository.UnidadeDeMedidaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;
import org.springframework.web.util.UriComponentsBuilder;

import javax.persistence.EntityNotFoundException;
import java.math.BigDecimal;
import java.net.URI;
import java.util.List;
import java.util.UUID;

@Service
public class TalhaoService {

    @Autowired
    private TalhaoRepository talhaoRepository;

    @Autowired
    private PropriedadeRepository propriedadeRepository;

    @Autowired
    private UnidadeDeMedidaRepository unidadeDeMedidaRepository;

    public Page<Talhao> findAll(Pageable paginacao) {
        return this.talhaoRepository.findAll(paginacao);
    }

    public Talhao findByIdDTO(UUID id) {
        return this.talhaoRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Talhão não encontrado"));
    }

    public Talhao findById(UUID id) {
        return this.talhaoRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Talhão não encontrado!"));
    }

    public URI save(Talhao talhao, UriComponentsBuilder uriBuilder) {
        this.talhaoRepository.save(talhao);
        return uriBuilder.path("/talhao/{id}").buildAndExpand(talhao.getId()).toUri();
    }

    public Talhao update(UUID id, Talhao talhaoAt) {
        return this.talhaoRepository.findById(id)
                .map(talhao -> {
                    Talhao talhaoAtualizado = this.talhaoRepository.save(this.retornaTalhaoAposAtualizacao(talhaoAt, talhao));
                    return talhaoAtualizado;
                })
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Talhão não encontrado"));
    }

    public void delete(UUID id) {
        try {
            this.talhaoRepository.deleteById(id);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    private Talhao retornaTalhaoAposAtualizacao(Talhao talhaoAt, Talhao talhao) {

        if(!talhaoAt.getPropriedade().getId().toString().isEmpty() && talhaoAt.getPropriedade().getId().toString() != null) {
            Propriedade propriedade = this.propriedadeRepository.findById(talhaoAt.getPropriedade().getId())
                    .orElseThrow(() -> new EntityNotFoundException("Propriedade de id: " + talhaoAt.getPropriedade().getId().toString() + " não encontrada!"));
            talhao.setPropriedade(propriedade);
        }

        if(!talhaoAt.getIdentificacao().isEmpty() && talhaoAt.getIdentificacao() != null) {
            talhao.setIdentificacao(talhaoAt.getIdentificacao());
        }

        if(talhaoAt.getArea().compareTo(BigDecimal.ZERO) > 0) {
            talhao.setArea(talhaoAt.getArea());
        }

        if(!talhaoAt.getCoordenadas().isEmpty() && talhaoAt.getCoordenadas() != null) {
            talhao.setCoordenadas(talhaoAt.getCoordenadas());
        }

        if(!talhaoAt.getUnidadeDeMedida().getId().toString().isEmpty() && talhaoAt.getUnidadeDeMedida().getId().toString() != null) {
            UnidadeDeMedida unidadeDeMedida = this.unidadeDeMedidaRepository.findById(talhaoAt.getUnidadeDeMedida().getId())
                    .orElseThrow(() -> new EntityNotFoundException("Unidade de medidade de id: " + talhaoAt.getUnidadeDeMedida().getId().toString() + " não encontrada"));
            talhao.setUnidadeDeMedida(unidadeDeMedida);
        }

        return talhao;

    }

    public List<Talhao> findAll() {
        return this.talhaoRepository.findAll();
    }

    public List<Talhao> findByIdPropriedade(String id) {
        return this.talhaoRepository.findAllByPropriedade(UUID.fromString(id));
    }
}
