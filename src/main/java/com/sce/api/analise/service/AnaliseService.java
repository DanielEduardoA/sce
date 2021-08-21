package com.sce.api.analise.service;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sce.api.analise.model.AnaliseResponse;
import com.sce.api.analise.model.AnaliseVo;
import com.sce.persistence.analise.entity.Analise;
import com.sce.persistence.analise.repository.AnaliseRepository;

@Service
public class AnaliseService {

    @Autowired
    private AnaliseRepository analiseRepository;

    public List<Analise> listAll() {
        return StreamSupport.stream(
                analiseRepository.findAll().spliterator(),
                false)
                .collect(Collectors.toList());
    }

    public AnaliseResponse save(AnaliseVo vo) {
         Analise analise = analiseRepository.save(buildAnalise(vo));
         return buildAnaliseResponse(analise);
    }
    
    private Analise buildAnalise(AnaliseVo vo) {
        return Analise.builder()
                .dataConclusao(vo.getDataConclusao())
                .dataEntrada(vo.getDataEntrada())
                .laboratorio(vo.getLaboratorio())
                .laboratorista(vo.getLaboratorista())
                .build();
    }

    private AnaliseResponse buildAnaliseResponse(Analise analise) {
        return AnaliseResponse.builder()
                .dataConclusao(analise.getDataConclusao())
                .dataEntrada(analise.getDataEntrada())
                .id(analise.getId())
                .laboratorio(analise.getLaboratorio())
                .laboratorista(analise.getLaboratorista())
                .build();
    }
}
