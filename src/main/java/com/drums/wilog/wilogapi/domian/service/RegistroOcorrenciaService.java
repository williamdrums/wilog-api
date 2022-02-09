package com.drums.wilog.wilogapi.domian.service;

import com.drums.wilog.wilogapi.domian.model.Entrega;
import com.drums.wilog.wilogapi.domian.model.Ocorrencia;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@AllArgsConstructor
@Service
public class RegistroOcorrenciaService {

    private BuscaEntregaService buscaEntregaService;

    @Transactional
    public Ocorrencia register(Long entregaId, String descricao) {
        Entrega entrega = buscaEntregaService.find(entregaId);

        return entrega.adicionarOcorrencia(descricao);
    }
}
