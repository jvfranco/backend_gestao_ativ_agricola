package com.gestao.agricola.controller;

import com.gestao.agricola.model.Hibrido;
import com.gestao.agricola.service.HibridoService;
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
@RequestMapping("/cultivar")
public class HibridoController {

    @Autowired
    private HibridoService hibridoService;

    @GetMapping("/todos")
    public ResponseEntity<Page<Hibrido>> retornarTodasCultivares(@PageableDefault(sort = "id", direction = Sort.Direction.ASC, page = 0, size = 10) Pageable paginacao) {
        Page<Hibrido> pageCultivares = this.hibridoService.findAll(paginacao);
        return ResponseEntity.ok(pageCultivares);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Hibrido> retornarCultivarDetalhada(@PathVariable String id) {
        Hibrido hibrido = this.hibridoService.findById(UUID.fromString(id));
        return ResponseEntity.ok(hibrido);
    }

    @PostMapping()
    public ResponseEntity<Hibrido> salvarNovaCultivar(@RequestBody @Valid Hibrido hibrido, UriComponentsBuilder uriBuilder) {
        URI uri = this.hibridoService.save(hibrido, uriBuilder);
        return ResponseEntity.created(uri).body(hibrido);
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> atualizarCultivar(@PathVariable String id, @RequestBody Hibrido hibrido) {
        this.hibridoService.update(UUID.fromString(id), hibrido);
        return ResponseEntity.noContent().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> excluirCultura(@PathVariable String id) {
        this.hibridoService.delete(UUID.fromString(id));
        return ResponseEntity.noContent().build();
    }

}
