package com.gestao.agricola.service;

import com.gestao.agricola.model.Ocupacao;
import com.gestao.agricola.model.Pessoa;
import com.gestao.agricola.repository.PessoaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class PessoaService {

    @Autowired
    private PessoaRepository pessoaRepository;

    public Page<Pessoa> findAll(Pageable paginacao) {
        return this.pessoaRepository.findAll(paginacao);
    }

    public Optional<Pessoa> findById(UUID id) {
        return this.pessoaRepository.findById(id);
    }

    public URI save(Pessoa pessoa, UriComponentsBuilder uriBuilder) {
        this.pessoaRepository.save(pessoa);
        return uriBuilder.path("/pessoa/{id}").buildAndExpand(pessoa.getId()).toUri();
    }

    public Optional<Object> update(UUID id, Pessoa pessoaUpdate) {
        return this.pessoaRepository.findById(id)
                .map(pes -> {
                    return Optional.ofNullable(
                            this.pessoaRepository.save(
                                    this.retornaPessoaAposUpdate(pessoaUpdate, pes)));
                });
    }

    public void delete(UUID id) {
        try {
            this.pessoaRepository.deleteById(id);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    private Pessoa retornaPessoaAposUpdate(Pessoa pessoaUpdate, Pessoa pessoa) {

        if(!pessoaUpdate.getNome().isEmpty() && pessoaUpdate.getNome() != null) {
            pessoa.setNome(pessoaUpdate.getNome());
        }

        if(!pessoaUpdate.getCpf().isEmpty() && pessoaUpdate.getCpf() != null) {
            pessoa.setCpf(pessoaUpdate.getCpf());
        }

        if(!pessoaUpdate.getTelefone().isEmpty() && pessoaUpdate.getTelefone() != null) {
            pessoa.setTelefone(pessoaUpdate.getTelefone());
        }

        if(!pessoaUpdate.getEmail().isEmpty() && pessoaUpdate.getEmail() != null) {
            pessoa.setEmail(pessoaUpdate.getEmail());
        }

        if(!pessoaUpdate.getOcupacao().toString().isEmpty() && pessoaUpdate.getOcupacao().toString() != null) {
            if(pessoaUpdate.getOcupacao().toString().equalsIgnoreCase(Ocupacao.PROPRIETARIO.toString())) {
                pessoa.setOcupacao(Ocupacao.PROPRIETARIO);
            } else {
                pessoa.setOcupacao(Ocupacao.FUNCIONARIO);
            }
        }

        return pessoa;
    }

    public List<Pessoa> findAll() {
        return this.pessoaRepository.findAll();
    }
}
