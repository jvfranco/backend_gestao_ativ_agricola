package com.gestao.agricola.service;

import com.gestao.agricola.model.AptAtividadeAgricola;
import com.gestao.agricola.repository.AptAtividadeAgricolaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;
import java.util.UUID;

@Service
public class AptAtividadeAgricolaService {

    @Autowired
    private AptAtividadeAgricolaRepository aptAtividadeAgricolaRepository;


    public URI save(AptAtividadeAgricola apontamento, UriComponentsBuilder uriBuilder) {
        this.aptAtividadeAgricolaRepository.save(apontamento);
        return uriBuilder.path("/apontamento/{id}").buildAndExpand(apontamento.getId()).toUri();
    }

    public AptAtividadeAgricola findById(UUID id) {
        return this.aptAtividadeAgricolaRepository.findById(id).orElseThrow(
                () -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Apontamento não encontrado!")
        );
    }
}
