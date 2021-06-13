package com.gestao.agricola.controller;

import com.gestao.agricola.model.UnidadeDeMedida;
import com.gestao.agricola.service.UnidadeDeMedidaService;
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
import java.util.UUID;

@RestController
@RequestMapping("/unidadeDeMedida")
public class UnidadeDeMedidaController {

    @Autowired
    private UnidadeDeMedidaService unidadeDeMedidaService;

    @GetMapping("/todos")
    public ResponseEntity<Page<UnidadeDeMedida>> retornarTodasUnidades(@PageableDefault(sort = "id", direction = Sort.Direction.ASC, page = 0, size = 10) Pageable paginacao) {
        Page<UnidadeDeMedida> pageUnidades = this.unidadeDeMedidaService.findAll(paginacao);
        return ResponseEntity.ok(pageUnidades);
    }

    @GetMapping("/{id}")
    public ResponseEntity<UnidadeDeMedida> retornarUnidadeDetalhada(@PathVariable String id) {
        UnidadeDeMedida unidadeDeMedida = this.unidadeDeMedidaService.findById(UUID.fromString(id));
        return ResponseEntity.ok(unidadeDeMedida);
    }

    @PostMapping()
    public ResponseEntity<UnidadeDeMedida> salvarNovaUnidade(@RequestBody @Valid UnidadeDeMedida unidade, UriComponentsBuilder uriBuilder) {
        URI uri = this.unidadeDeMedidaService.save(unidade, uriBuilder);
        return ResponseEntity.created(uri).body(unidade);
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> atualizarUnidade(@PathVariable String id, @RequestBody UnidadeDeMedida unidade) {
        this.unidadeDeMedidaService.update(UUID.fromString(id), unidade);
        return ResponseEntity.noContent().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> excluirUnidade(@PathVariable String id) {
        this.unidadeDeMedidaService.delete(UUID.fromString(id));
        return ResponseEntity.noContent().build();
    }

}
