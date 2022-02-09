package com.drums.wilog.wilogapi.domian.service;

import com.drums.wilog.wilogapi.domian.exception.EntidadeNaoEncontradaException;
import com.drums.wilog.wilogapi.domian.exception.RegraNegocioException;
import com.drums.wilog.wilogapi.domian.model.Entrega;
import com.drums.wilog.wilogapi.domian.repository.EntregaRepository;
import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Data
@Service
public class BuscaEntregaService {

    @Autowired
    private EntregaRepository entregaRepository;

    public Entrega find(Long entregaId) {
        return entregaRepository.findById(entregaId)
                .orElseThrow(() -> new EntidadeNaoEncontradaException("Entrega não encontrada"));
    }
}
