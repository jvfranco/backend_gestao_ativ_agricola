package com.gestao.agricola.service;

import com.gestao.agricola.model.Talhao;
import com.gestao.agricola.model.dto.TalhaoDTO;
import com.gestao.agricola.model.form.TalhaoForm;
import com.gestao.agricola.repository.TalhaoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;
import java.time.LocalDate;
import java.util.Optional;
import java.util.UUID;

@Service
public class TalhaoService {

    @Autowired
    private TalhaoRepository talhaoRepository;

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
                    talhao.setIdentificacao(talhaoForm.getIdentificacao());
                    talhao.setArea(talhaoForm.getArea());
                    talhao.setCoordenadas(talhaoForm.getCoordenadas());
                    talhao.setDataAtualizacao(LocalDate.now());
                    return this.talhaoRepository.save(talhao);
                });
    }

    public boolean delete(UUID id) {
        this.talhaoRepository.findById(id)
                .map(talhao -> {
                    this.talhaoRepository.delete(talhao);
                    return true;
                });
        return false;
    }
}
