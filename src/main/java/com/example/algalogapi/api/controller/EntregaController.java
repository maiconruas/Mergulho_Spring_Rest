package com.example.algalogapi.api.controller;

import com.example.algalogapi.api.assembler.EntregaAssembler;
import com.example.algalogapi.api.model.EntregaModel;
import com.example.algalogapi.api.model.input.EntregaInput;
import com.example.algalogapi.domain.model.Entrega;
import com.example.algalogapi.domain.repository.EntregaRepository;
import com.example.algalogapi.domain.services.FinalizacaoEntregaService;
import com.example.algalogapi.domain.services.SolicitacaoEntregaService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import javax.validation.Valid;
import java.util.List;

@AllArgsConstructor
@RestController
@RequestMapping("/entregas")
public class EntregaController {

    private EntregaRepository entregaRepository;
    private FinalizacaoEntregaService finalizacaoEntregaService;
    private SolicitacaoEntregaService solicitacaoEntregaService;
    private EntregaAssembler entregaAssembler;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    private EntregaModel solicitar(@Valid @RequestBody EntregaInput entregaInput) {
        Entrega novaEntrega = entregaAssembler.toEntity(entregaInput);
        Entrega entregaSolicitada = solicitacaoEntregaService.solicitar(novaEntrega);
        return entregaAssembler.toModel(entregaSolicitada);
    }

    @GetMapping
    public List<EntregaModel> listar() {
        return entregaAssembler.toCollectionModel(entregaRepository.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<EntregaModel> buscar (@PathVariable Long id) {
        return entregaRepository.findById(id)
                .map(entrega ->  ResponseEntity.ok(entregaAssembler.toModel(entrega)))
                .orElse(ResponseEntity.notFound().build());
    }

    @PutMapping("/{id}/finalizacao")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void finalizar(@PathVariable Long id) {
        finalizacaoEntregaService.finalizar(id);
    }

}
