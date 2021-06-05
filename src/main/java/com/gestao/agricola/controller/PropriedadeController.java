package com.gestao.agricola.controller;

import com.gestao.agricola.model.Propriedade;
import com.gestao.agricola.model.dto.PropriedadeDTO;
import com.gestao.agricola.model.form.PropriedadeForm;
import com.gestao.agricola.repository.TalhaoRepository;
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
import java.util.UUID;

@RestController
@RequestMapping("/propriedade")
public class PropriedadeController {

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Autowired
    private TalhaoRepository talhaoRepository;

    @Autowired
    private PropriedadeService propriedadeService;

    @GetMapping("/todos")
    public ResponseEntity<Page<PropriedadeDTO>> retornarTodosUsuarios(@PageableDefault(sort = "id", direction = Sort.Direction.ASC, page = 0, size = 10) Pageable paginacao) {
        Page<PropriedadeDTO> pagePropriedades = this.propriedadeService.findAll(paginacao);

        return ResponseEntity.ok().body(pagePropriedades);
    }

    @GetMapping("/{id}")
    public ResponseEntity<PropriedadeDTO> retornarPropriedadeDetalhada(@PathVariable UUID id) {
        PropriedadeDTO propriedadeDTO = this.propriedadeService.findById(id)
            .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Propriedade não encontrada"));

        return ResponseEntity.ok().body(propriedadeDTO);
    }

    @PostMapping
    public ResponseEntity<PropriedadeDTO> salvarNovaPropriedade(@RequestBody @Valid PropriedadeForm propriedadeForm, UriComponentsBuilder uriBuilder) {
        Propriedade propriedade = PropriedadeForm.converter(propriedadeForm, this.usuarioRepository, this.talhaoRepository);
        URI uri = this.propriedadeService.save(propriedade, uriBuilder);
        return ResponseEntity.created(uri).body(new PropriedadeDTO(propriedade));
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> atualizarPropriedade(@PathVariable UUID id, @RequestBody PropriedadeForm propriedadeForm) {
        this.propriedadeService.update(id, propriedadeForm, usuarioRepository, talhaoRepository)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Propriedade não encontrada"));

        return ResponseEntity.noContent().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> excluirPropriedade(@PathVariable UUID id) {
        return this.propriedadeService.delete(id)
                ? ResponseEntity.noContent().build()
                : ResponseEntity.notFound().build();
    }

}
