package com.sce.api.especie.service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sce.api.especie.model.EspecieResponse;
import com.sce.persistence.especie.entity.Especie;
import com.sce.persistence.especie.repository.EspecieRepository;

@Service
public class EspecieService {

    @Autowired
    private EspecieRepository especieRepository;

    public List<EspecieResponse> listAll() {
        List<EspecieResponse> response = new ArrayList<>();
        List<Especie> especies = StreamSupport.stream(especieRepository.findAll().spliterator(), false).collect(Collectors.toList());
        especies.stream().forEach(especie -> response.add(buildEspecieResponse(especie)));
        return response;
    }

    private EspecieResponse buildEspecieResponse(Especie especie) {
        return EspecieResponse.builder()
                .id(especie.getId())
                .nome(especie.getNome())
                .build();
    }
}
