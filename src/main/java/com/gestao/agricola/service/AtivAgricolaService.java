package com.gestao.agricola.service;

import com.gestao.agricola.model.AtividadeAgricola;
import com.gestao.agricola.repository.AtividadeAgricolaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;
import java.time.LocalDate;
import java.util.UUID;

@Service
public class AtivAgricolaService {

    @Autowired
    private AtividadeAgricolaRepository atividadeAgricolaRepository;

    public Page<AtividadeAgricola> findAll(Pageable paginacao) {
        return this.atividadeAgricolaRepository.findAll(paginacao);
    }

    public AtividadeAgricola findById(UUID id) {
        return this.atividadeAgricolaRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Atividade Agricola não encontrada!"));
    }

    public URI save(AtividadeAgricola atividadeAgricola, UriComponentsBuilder uriBuilder) {
        this.atividadeAgricolaRepository.save(atividadeAgricola);
        return uriBuilder.path("/atividadeAgricola/{id}").buildAndExpand(atividadeAgricola.getId()).toUri();
    }

    public void update(UUID id, AtividadeAgricola ativAgricAtualizada) {
        AtividadeAgricola atividadeAgricola = this.findById(id);
        this.atividadeAgricolaRepository.save(this.retornaAtividadeAgricolaAposAtualizacao(ativAgricAtualizada, atividadeAgricola));
    }

    private AtividadeAgricola retornaAtividadeAgricolaAposAtualizacao(AtividadeAgricola ativAgricAtualizada, AtividadeAgricola atividadeAgricola) {
        if(!ativAgricAtualizada.getDescricao().isEmpty() && ativAgricAtualizada.getDescricao() != null) {
            atividadeAgricola.setDescricao(ativAgricAtualizada.getDescricao());
        }

        atividadeAgricola.setDataAtualizacao(LocalDate.now());

        return atividadeAgricola;
    }

    public void delete(UUID id) {
        try {
            this.atividadeAgricolaRepository.deleteById(id);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }
}
