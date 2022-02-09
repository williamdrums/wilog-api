package com.drums.wilog.wilogapi.domian.service;

import com.drums.wilog.wilogapi.domian.model.Entrega;
import com.drums.wilog.wilogapi.domian.repository.EntregaRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@AllArgsConstructor
@Service
public class FinalizacaoEntregaService {

    private EntregaRepository entregaRepository;
    private BuscaEntregaService buscaEntregaService;

    @Transactional
    public void finalizationDelivery(Long entregaId) {
        Entrega entrega = buscaEntregaService.find(entregaId);

        entrega.finalizar();

        entregaRepository.save(entrega);
    }
}
