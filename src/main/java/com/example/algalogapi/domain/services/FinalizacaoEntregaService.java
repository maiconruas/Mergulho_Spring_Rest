package com.example.algalogapi.domain.services;

import com.example.algalogapi.domain.model.Entrega;
import com.example.algalogapi.domain.repository.EntregaRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@AllArgsConstructor
public class FinalizacaoEntregaService {

    private EntregaRepository entregaRepository;
    private BuscaEntregaService buscaEntregaService;

    @Transactional
    public void finalizar(Long id) {
        Entrega entrega = buscaEntregaService.buscar(id);
        entrega.finalizar();
        entregaRepository.save(entrega);
    }
}
