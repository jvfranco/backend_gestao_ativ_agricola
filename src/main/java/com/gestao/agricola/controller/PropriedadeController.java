package com.gestao.agricola.controller;

import com.gestao.agricola.model.Propriedade;
import com.gestao.agricola.model.form.PropriedadeForm;
import com.gestao.agricola.repository.PessoaRepository;
import com.gestao.agricola.repository.UnidadeDeMedidaRepository;
import com.gestao.agricola.repository.UsuarioRepository;
import com.gestao.agricola.service.PropriedadeService;
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
@RequestMapping("/propriedade")
public class PropriedadeController {

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Autowired
    private UnidadeDeMedidaRepository unidadeDeMedidaRepository;

    @Autowired
    private PropriedadeService propriedadeService;

    @Autowired
    private PessoaRepository pessoaRepository;

    @GetMapping("/todos")
    public ResponseEntity<Page<Propriedade>> retornarTodosPropriedades(@PageableDefault(sort = "id", direction = Sort.Direction.ASC, page = 0, size = 10) Pageable paginacao) {
        Page<Propriedade> pagePropriedades = this.propriedadeService.findAll(paginacao);

        return ResponseEntity.ok(pagePropriedades);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Propriedade> retornarPropriedadeDetalhada(@PathVariable String id) {
        Propriedade propriedade = this.propriedadeService.findById(UUID.fromString(id))
            .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Propriedade não encontrada"));

        return ResponseEntity.ok(propriedade);
    }

    @GetMapping()
    public ResponseEntity<List<Propriedade>> retornarTodosPropriedadesSemPaginacao() {
        List<Propriedade> propriedades = this.propriedadeService.findAll();

        return ResponseEntity.ok(propriedades);
    }

    @PostMapping
    public ResponseEntity<Propriedade> salvarNovaPropriedade(@RequestBody @Valid PropriedadeForm propriedadeForm, UriComponentsBuilder uriBuilder) {
        Propriedade propriedade = PropriedadeForm.converter(propriedadeForm, this.pessoaRepository, this.unidadeDeMedidaRepository);
        URI uri = this.propriedadeService.save(propriedade, uriBuilder);
        return ResponseEntity.created(uri).body(propriedade);
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> atualizarPropriedade(@PathVariable UUID id, @RequestBody PropriedadeForm propriedadeForm) {
        this.propriedadeService.update(id, propriedadeForm)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Propriedade não encontrada"));

        return ResponseEntity.noContent().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> excluirPropriedade(@PathVariable UUID id) {
        this.propriedadeService.delete(id);
        return ResponseEntity.noContent().build();
    }

}
