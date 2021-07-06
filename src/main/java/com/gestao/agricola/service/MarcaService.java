package com.gestao.agricola.service;

import com.gestao.agricola.model.Marca;
import com.gestao.agricola.repository.MarcaRepository;
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
public class MarcaService {

    @Autowired
    private MarcaRepository marcaRepository;

    public Page<Marca> findAll(Pageable paginacao) {
        return this.marcaRepository.findAll(paginacao);
    }

    public Marca findById(UUID id) {
        return this.marcaRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Marca não encontrada!"));
    }

    public URI save(Marca marca, UriComponentsBuilder uriBuilder) {
        this.marcaRepository.save(marca);
        return uriBuilder.path("/marca/{id}").buildAndExpand(marca.getId()).toUri();
    }

    public void update(UUID id, Marca marcaAtualizada) {
        Marca marca = this.findById(id);
        this.marcaRepository.save(this.retornaUnidadeAposAtualizacao(marcaAtualizada, marca));
    }

    private Marca retornaUnidadeAposAtualizacao(Marca marcaAtualizada, Marca marca) {
        if(!marcaAtualizada.getNome().isEmpty() && marcaAtualizada.getNome() != null) {
            marca.setNome(marcaAtualizada.getNome());
        }

        return marca;
    }

    public void delete(UUID id) {
        try {
            this.marcaRepository.deleteById(id);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    public List<Marca> findAll() {
        return this.marcaRepository.findAll();
    }
}
