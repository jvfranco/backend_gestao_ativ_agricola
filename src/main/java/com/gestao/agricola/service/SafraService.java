package com.gestao.agricola.service;

import com.gestao.agricola.model.Safra;
import com.gestao.agricola.repository.SafraRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;
import java.util.List;
import java.util.UUID;

@Service
public class SafraService {

    @Autowired
    private SafraRepository safraRepository;

    public Page<Safra> findAll(Pageable paginacao) {
        return this.safraRepository.findAll(paginacao);
    }

    public Safra findById(UUID id) {
        return this.safraRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Safra não encontrada!"));
    }

    public URI save(Safra safra, UriComponentsBuilder uriBuilder) {
        this.safraRepository.save(safra);
        return uriBuilder.path("/safra/{id}").buildAndExpand(safra.getId()).toUri();
    }

    public void update(UUID id, Safra safraAtualizada) {
        Safra safra = this.findById(id);
        this.safraRepository.save(this.retornaUnidadeAposAtualizacao(safraAtualizada, safra));
    }

    private Safra retornaUnidadeAposAtualizacao(Safra safraAtualizada, Safra safra) {
        if(!safraAtualizada.getIdentificacao().isEmpty() && safraAtualizada.getIdentificacao() != null) {
            safra.setIdentificacao(safraAtualizada.getIdentificacao());
        }

        if(safraAtualizada.getDataInicial() != null) {
           safra.setDataInicial(safraAtualizada.getDataInicial());
        }

        if(safraAtualizada.getDataFinal() != null) {
            safra.setDataFinal(safraAtualizada.getDataFinal());
        }

        return safra;
    }

    public void delete(UUID id) {
        try {
            this.safraRepository.deleteById(id);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    public List<Safra> findAll() {
        return this.safraRepository.findAll();
    }
}
