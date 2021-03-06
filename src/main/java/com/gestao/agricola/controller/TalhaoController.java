package com.gestao.agricola.controller;

import com.gestao.agricola.model.Talhao;
import com.gestao.agricola.repository.PropriedadeRepository;
import com.gestao.agricola.repository.UnidadeDeMedidaRepository;
import com.gestao.agricola.service.TalhaoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import javax.validation.Valid;
import java.net.URI;
import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/talhao")
public class TalhaoController {

    @Autowired
    private TalhaoService talhaoService;

    @Autowired
    private PropriedadeRepository propriedadeRepository;

    @Autowired
    private UnidadeDeMedidaRepository unidadeDeMedidaRepository;

    @GetMapping("/todos")
    public ResponseEntity<Page<Talhao>> retornarTodosTalhoes(
            @PageableDefault(sort = "id", direction = Sort.Direction.ASC, page = 0, size = 10) Pageable paginacao) {
        Page<Talhao> pageTalhoes = this.talhaoService.findAll(paginacao);

        return ResponseEntity.ok(pageTalhoes);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Talhao> retornarTalhaoDetalhado(@PathVariable String id) {
        Talhao talhao = this.talhaoService.findById(UUID.fromString(id));

        return ResponseEntity.ok(talhao);
    }

    @GetMapping()
    public ResponseEntity<List<Talhao>> retornarTodosTalhoesSemPaginacao() {
        List<Talhao> talhoes = this.talhaoService.findAll();
        return ResponseEntity.ok(talhoes);
    }

    @GetMapping("/propriedade/{id}")
    public ResponseEntity<List<Talhao>> retornarTodosTalhoesSemPaginacaoPorPropriedade(@PathVariable String id) {
        List<Talhao> talhoes = this.talhaoService.findByIdPropriedade(id);
        return ResponseEntity.ok(talhoes);
    }

    @PostMapping()
    public ResponseEntity<Talhao> salvarNovoTalhao(@RequestBody @Valid Talhao talhao, UriComponentsBuilder uriBuilder) {
        URI uri = this.talhaoService.save(talhao, uriBuilder);

        return ResponseEntity.created(uri).body(talhao);
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> atualizarTalhao(@PathVariable String id, @RequestBody @Valid Talhao talhao) {
        this.talhaoService.update(UUID.fromString(id), talhao);

        return ResponseEntity.noContent().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> excluirTalhao(@PathVariable String id) {
        this.talhaoService.delete(UUID.fromString(id));
        return ResponseEntity.noContent().build();
    }

}
