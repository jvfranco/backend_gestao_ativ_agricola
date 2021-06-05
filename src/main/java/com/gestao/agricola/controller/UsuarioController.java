package com.gestao.agricola.controller;

import com.gestao.agricola.model.Usuario;
import com.gestao.agricola.model.dto.UsuarioDTO;
import com.gestao.agricola.model.form.UsuarioForm;
import com.gestao.agricola.service.UsuarioService;
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
@RequestMapping("/usuario")
public class UsuarioController {

    @Autowired
    private UsuarioService usuarioService;

    @GetMapping("/todos")
    public ResponseEntity<Page<UsuarioDTO>> retornarTodosUsuarios(@PageableDefault(sort = "id", direction = Sort.Direction.ASC, page = 0, size = 10) Pageable paginacao) {
        Page<UsuarioDTO> pageUsuarios = this.usuarioService.findAll(paginacao);

        return ResponseEntity.ok().body(pageUsuarios);
    }

    @GetMapping("/{id}")
    public ResponseEntity<UsuarioDTO> retornarUsuarioDetalhado(@PathVariable UUID id) {
        UsuarioDTO usuarioDTO = this.usuarioService.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Usuário não encontrado"));

        return ResponseEntity.ok().body(usuarioDTO);
    }

    //TODO criptografar senha
    @PostMapping()
    public ResponseEntity<UsuarioDTO> salvarNovoUsuario(@RequestBody @Valid UsuarioForm usuarioForm, UriComponentsBuilder uriBuilder) {
        Usuario usuario = UsuarioForm.converter(usuarioForm);
        URI uri = this.usuarioService.save(usuario, uriBuilder);

        return ResponseEntity.created(uri).body(new UsuarioDTO(usuario));
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> atualizarUsuario(@PathVariable UUID id, @RequestBody @Valid UsuarioForm usuarioForm) {
        this.usuarioService.update(id, usuarioForm)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Usuário não encontrado"));

        return ResponseEntity.noContent().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> excluirUsuario(@PathVariable UUID id) {
        return this.usuarioService.delete(id)
                ? ResponseEntity.noContent().build()
                : ResponseEntity.notFound().build();
    }

}
