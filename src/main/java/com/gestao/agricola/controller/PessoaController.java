package com.gestao.agricola.controller;

import com.gestao.agricola.model.Pessoa;
import com.gestao.agricola.service.PessoaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;
import org.springframework.web.util.UriComponentsBuilder;

import javax.validation.Valid;
import java.net.URI;
import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/pessoa")
public class PessoaController {

    @Autowired
    private PessoaService pessoaService;

    @GetMapping("/todos")
    public ResponseEntity<Page<Pessoa>> retornarTodasPessoas(@PageableDefault(sort = "id", direction = Sort.Direction.ASC, page = 0, size = 10) Pageable paginacao) {
        Page<Pessoa> pageUsuarios = this.pessoaService.findAll(paginacao);

        return ResponseEntity.ok(pageUsuarios);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Pessoa> retornarPessoaDetalhada(@PathVariable UUID id) {
        Pessoa pessoa = this.pessoaService.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Pessoa não encontrada"));

        return ResponseEntity.ok(pessoa);
    }

    @GetMapping()
    public ResponseEntity<List<Pessoa>> retornarTodasPessoasSemPaginacao() {
        List<Pessoa> pessoas = this.pessoaService.findAll();
        return ResponseEntity.ok(pessoas);
    }

    //TODO criptografar senha
    @PostMapping()
    public ResponseEntity<Pessoa> salvarNovaPessoa(@RequestBody @Valid Pessoa pessoa, UriComponentsBuilder uriBuilder) {
        URI uri = this.pessoaService.save(pessoa, uriBuilder);

        return ResponseEntity.created(uri).body(pessoa);
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> atualizarPessoa(@PathVariable UUID id, @RequestBody Pessoa pessoa) {
        this.pessoaService.update(id, pessoa)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Pessoa não encontrada"));

        return ResponseEntity.noContent().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> excluirPessoa(@PathVariable UUID id) {
        this.pessoaService.delete(id);

        return ResponseEntity.noContent().build();
    }

}
