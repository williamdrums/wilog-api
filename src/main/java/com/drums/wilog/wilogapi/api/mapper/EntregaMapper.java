package com.drums.wilog.wilogapi.api.mapper;

import com.drums.wilog.wilogapi.api.dto.EntregaDTO;
import com.drums.wilog.wilogapi.domian.model.Entrega;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@AllArgsConstructor
@Component
public class EntregaMapper {

    private ModelMapper modelMapper;

    public EntregaDTO toModel(Entrega entrega){
      return modelMapper.map(entrega,EntregaDTO.class);
    }

    public List<EntregaDTO> toCollectionModel(List<Entrega> entregas){
        return entregas.stream().map(this::toModel)
                .collect(Collectors.toList());
    }
}
