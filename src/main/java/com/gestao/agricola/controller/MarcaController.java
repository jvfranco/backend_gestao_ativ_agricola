package com.gestao.agricola.controller;

import com.gestao.agricola.model.Marca;
import com.gestao.agricola.service.MarcaService;
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
@RequestMapping("/marca")
public class MarcaController {

    @Autowired
    private MarcaService marcaService;

    @GetMapping("/todos")
    public ResponseEntity<Page<Marca>> retornarTodasMarcas(@PageableDefault(sort = "id", direction = Sort.Direction.ASC, page = 0, size = 10) Pageable paginacao) {
        Page<Marca> pageMarcas = this.marcaService.findAll(paginacao);
        return ResponseEntity.ok(pageMarcas);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Marca> retornarMarcaDetalhada(@PathVariable String id) {
        Marca marca = this.marcaService.findById(UUID.fromString(id));
        return ResponseEntity.ok(marca);
    }

    @GetMapping()
    public ResponseEntity<List<Marca>> retornarTodasMarcasSemPaginacao() {
        List<Marca> marcas = this.marcaService.findAll();
        return ResponseEntity.ok(marcas);
    }

    @PostMapping()
    public ResponseEntity<Marca> salvarNovaMarca(@RequestBody @Valid Marca marca, UriComponentsBuilder uriBuilder) {
        URI uri = this.marcaService.save(marca, uriBuilder);
        return ResponseEntity.created(uri).body(marca);
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> atualizarMarca(@PathVariable String id, @RequestBody Marca marca) {
        this.marcaService.update(UUID.fromString(id), marca);
        return ResponseEntity.noContent().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> excluirMarca(@PathVariable String id) {
        this.marcaService.delete(UUID.fromString(id));
        return ResponseEntity.noContent().build();
    }

}
