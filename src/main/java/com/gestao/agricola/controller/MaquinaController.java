package com.gestao.agricola.controller;

import com.gestao.agricola.model.Maquina;
import com.gestao.agricola.service.MaquinaService;
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
@RequestMapping("/maquina")
public class MaquinaController {

    @Autowired
    private MaquinaService maquinaService;

    @GetMapping("/todos")
    public ResponseEntity<Page<Maquina>> retornarTodasMaquina(@PageableDefault(sort = "id", direction = Sort.Direction.ASC, page = 0, size = 10) Pageable paginacao) {
        Page<Maquina> pageMaquinas = this.maquinaService.findAll(paginacao);
        return ResponseEntity.ok(pageMaquinas);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Maquina> retornarMaquinaDetalhada(@PathVariable String id) {
        Maquina maquina = this.maquinaService.findById(UUID.fromString(id));
        return ResponseEntity.ok(maquina);
    }

    @PostMapping()
    public ResponseEntity<Maquina> salvarNovaMaquina(@RequestBody @Valid Maquina maquina, UriComponentsBuilder uriBuilder) {
        URI uri = this.maquinaService.save(maquina, uriBuilder);
        return ResponseEntity.created(uri).body(maquina);
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> atualizarMaquina(@PathVariable String id, @RequestBody Maquina maquina) {
        this.maquinaService.update(UUID.fromString(id), maquina);
        return ResponseEntity.noContent().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> excluirMarca(@PathVariable String id) {
        this.maquinaService.delete(UUID.fromString(id));
        return ResponseEntity.noContent().build();
    }

}
