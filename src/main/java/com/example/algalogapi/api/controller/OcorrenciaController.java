package com.example.algalogapi.api.controller;

import com.example.algalogapi.api.assembler.OcoorencaAssembler;
import com.example.algalogapi.api.model.OcorrenciaModel;
import com.example.algalogapi.api.model.input.OcorrenciaInput;
import com.example.algalogapi.domain.model.Entrega;
import com.example.algalogapi.domain.model.Ocorrencia;
import com.example.algalogapi.domain.services.BuscaEntregaService;
import com.example.algalogapi.domain.services.RegistroOcorrenciaService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.GetMapping;

import javax.validation.Valid;
import java.util.List;

@AllArgsConstructor
@RestController
@RequestMapping("entregas/{id}/ocorrencias")
public class OcorrenciaController {

    private BuscaEntregaService buscaEntregaService;
    private RegistroOcorrenciaService registroOcorrenciaService;
    private OcoorencaAssembler ocoorencaAssembler;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public OcorrenciaModel registrar(@PathVariable Long id,
         @Valid @RequestBody OcorrenciaInput ocorrenciaInput) {

       Ocorrencia ocorrenciaRegistrada = registroOcorrenciaService
        .registrar(id, ocorrenciaInput.getDescricao());
       return ocoorencaAssembler.toModel(ocorrenciaRegistrada);
    }

    @GetMapping
    public List<OcorrenciaModel> listar(@PathVariable Long id) {
        Entrega entrega = buscaEntregaService.buscar(id);
        return ocoorencaAssembler.toCollectionModel(entrega.getOcorrencias());
    }
}
