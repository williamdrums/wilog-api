package com.drums.wilog.wilogapi.api.mapper;

import com.drums.wilog.wilogapi.api.dto.OcorrenciaDTO;
import com.drums.wilog.wilogapi.domian.model.Ocorrencia;
import lombok.AllArgsConstructor;
import lombok.Data;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@AllArgsConstructor
@Component
public class OcorrenciaMapper {

    private ModelMapper modelMapper;

    public OcorrenciaDTO toModel(Ocorrencia ocorrencia) {
        return modelMapper.map(ocorrencia, OcorrenciaDTO.class);
    }

    public List<OcorrenciaDTO> toCollectionModel(List<Ocorrencia> ocorrencias) {
        return ocorrencias.stream()
                .map(this::toModel)
                .collect(Collectors.toList());
    }
}
