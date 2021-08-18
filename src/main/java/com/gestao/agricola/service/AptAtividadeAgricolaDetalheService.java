package com.gestao.agricola.service;

import com.gestao.agricola.model.AptAtividadeAgricolaDetalhe;
import com.gestao.agricola.repository.AptAtividadeAgricolaDetalheRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;
import java.util.List;
import java.util.UUID;

@Service
public class AptAtividadeAgricolaDetalheService {

    @Autowired
    private AptAtividadeAgricolaDetalheRepository aptAtividadeAgricolaDetalheRepository;


    public URI save(AptAtividadeAgricolaDetalhe detalhe, UriComponentsBuilder uriBuilder) {
        this.aptAtividadeAgricolaDetalheRepository.save((detalhe));
        return uriBuilder.path("/detalhe/{id}").buildAndExpand(detalhe.getId()).toUri();
    }

    public List<AptAtividadeAgricolaDetalhe> findAllByApontamento(String idApontamento) {
        return this.aptAtividadeAgricolaDetalheRepository.findAllByApontamento(UUID.fromString(idApontamento));
    }

    public List<AptAtividadeAgricolaDetalhe> findAllSemPaginacao() {
        return this.aptAtividadeAgricolaDetalheRepository.findAll();
    }
}
