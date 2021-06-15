package com.gestao.agricola.controller;

import com.gestao.agricola.model.Insumo;
import com.gestao.agricola.service.InsumoService;
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
@RequestMapping("/insumo")
public class InsumoController {

    @Autowired
    private InsumoService insumoService;

    @GetMapping("/todos")
    public ResponseEntity<Page<Insumo>> retornarTodasInsumos(@PageableDefault(sort = "id", direction = Sort.Direction.ASC, page = 0, size = 10) Pageable paginacao) {
        Page<Insumo> pageInsumos = this.insumoService.findAll(paginacao);
        return ResponseEntity.ok(pageInsumos);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Insumo> retornarInsumoDetalhada(@PathVariable String id) {
        Insumo insumo = this.insumoService.findById(UUID.fromString(id));
        return ResponseEntity.ok(insumo);
    }

    @PostMapping()
    public ResponseEntity<Insumo> salvarNovaInsumo(@RequestBody @Valid Insumo insumo, UriComponentsBuilder uriBuilder) {
        URI uri = this.insumoService.save(insumo, uriBuilder);
        return ResponseEntity.created(uri).body(insumo);
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> atualizarInsumo(@PathVariable String id, @RequestBody Insumo insumo) {
        this.insumoService.update(UUID.fromString(id), insumo);
        return ResponseEntity.noContent().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> excluirInsumo(@PathVariable String id) {
        this.insumoService.delete(UUID.fromString(id));
        return ResponseEntity.noContent().build();
    }

}
