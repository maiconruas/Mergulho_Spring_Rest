package com.example.algalogapi.domain.services;

import com.example.algalogapi.domain.exception.EntidadeNaoEncontradaException;
import com.example.algalogapi.domain.model.Entrega;
import com.example.algalogapi.domain.repository.EntregaRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@AllArgsConstructor
@Service
public class BuscaEntregaService {

    private EntregaRepository entregaRepository;

    public Entrega buscar(Long id) {
        return entregaRepository.findById(id)
                .orElseThrow(() -> new EntidadeNaoEncontradaException("Entrega não encontrada"));
    }
}
