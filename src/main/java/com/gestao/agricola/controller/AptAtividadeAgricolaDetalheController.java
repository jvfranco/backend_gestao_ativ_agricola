package com.gestao.agricola.controller;

import com.gestao.agricola.model.AptAtividadeAgricolaDetalhe;
import com.gestao.agricola.model.form.AptAtividadeAgricolaDetalheForm;
import com.gestao.agricola.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import javax.validation.Valid;
import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/apt-atividade-det")
public class AptAtividadeAgricolaDetalheController {

    @Autowired
    private AptAtividadeAgricolaDetalheService aptAtividadeAgricolaDetalheService;

    @Autowired
    private AptAtividadeAgricolaService aptAtividadeAgricolaService;

    @Autowired
    private MaquinaService maquinaService;

    @Autowired
    private InsumoService insumoService;

    @Autowired
    private HibridoService hibridoService;

    @Autowired
    private PessoaService pessoaService;

    @GetMapping("/detalhes/{idApontamento}")
    public ResponseEntity<List<AptAtividadeAgricolaDetalhe>> recuperarTodosDetalhesPorApontamento(@PathVariable String idApontamento) {
        List<AptAtividadeAgricolaDetalhe> detalhes = this.aptAtividadeAgricolaDetalheService.findAllByApontamento(idApontamento);
        return ResponseEntity.ok(detalhes);
    }

    @PostMapping()
    public ResponseEntity<AptAtividadeAgricolaDetalhe> salvarAptAtivAgricolaDetalhe(@RequestBody @Valid AptAtividadeAgricolaDetalheForm detalheFormForm, UriComponentsBuilder uriBuilder) {
        AptAtividadeAgricolaDetalhe detalhe = detalheFormForm.converte(aptAtividadeAgricolaService, maquinaService, insumoService, hibridoService, pessoaService);
        URI uri = this.aptAtividadeAgricolaDetalheService.save(detalhe, uriBuilder);
        return ResponseEntity.created(uri).body(detalhe);
    }
}
