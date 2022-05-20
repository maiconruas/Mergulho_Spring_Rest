package com.example.algalogapi.domain.services;

import com.example.algalogapi.domain.exception.NegocioException;
import com.example.algalogapi.domain.model.Cliente;
import com.example.algalogapi.domain.repository.ClienteRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@AllArgsConstructor
@Service
public class CatalogoClienteService {

    private ClienteRepository clienteRepository;


    @Transactional
    public Cliente salvar(Cliente cliente) {
        boolean emailEmUso = clienteRepository.findByEmail(cliente.getEmail())
                .stream()
                .anyMatch(clienteExistente -> !clienteExistente.equals(cliente));

        if (emailEmUso) {
            throw new NegocioException("Já existe um cliente cadastrado com este e-mail.");
        }

        return clienteRepository.save(cliente);
    }

    @Transactional
    public void deletar(Long id) {
        clienteRepository.deleteById(id);
    }


    public Cliente buscar(Long id) {
        return clienteRepository.findById(id)
                .orElseThrow(() -> new NegocioException("Cliente não encontrado"));
    }
}
