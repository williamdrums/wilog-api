package com.drums.wilog.wilogapi.domian.service;

import com.drums.wilog.wilogapi.domian.model.Cliente;
import com.drums.wilog.wilogapi.domian.model.Entrega;
import com.drums.wilog.wilogapi.domian.model.StatusEntrega;
import com.drums.wilog.wilogapi.domian.repository.EntregaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.time.OffsetDateTime;

@Service
public class SolicitacaoEntregaService {

    @Autowired
    private EntregaRepository entregaRepository;

    @Autowired
    private ClienteService clienteService;

    @Transactional
    public Entrega createDelivery(Entrega entrega) {
        Cliente cliente = clienteService.find(entrega.getCliente().getId());
        entrega.setCliente(cliente);
        entrega.setStatusEntrega(StatusEntrega.PENDENTE);
        entrega.setDataPedido(OffsetDateTime.now());
        return entregaRepository.save(entrega);
    }
}
