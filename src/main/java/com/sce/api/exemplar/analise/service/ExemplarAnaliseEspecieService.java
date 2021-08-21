package com.sce.api.exemplar.analise.service;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sce.api.exemplar.analise.exception.ExemplarAnaliseApiException;
import com.sce.api.exemplar.analise.model.ExemplarAnaliseEspecieResponse;
import com.sce.api.exemplar.analise.model.ExemplarAnaliseEspecieVo;
import com.sce.persistence.analise.entity.Analise;
import com.sce.persistence.especie.entity.Especie;
import com.sce.persistence.exemplar.analise.especie.entity.ExemplarAnaliseEspecie;
import com.sce.persistence.exemplar.analise.especie.entity.ExemplarAnaliseEspecieId;
import com.sce.persistence.exemplar.analise.especie.repository.ExemplarAnaliseEspecieRepository;
import com.sce.persistence.exemplar.entity.Exemplar;

@Service
public class ExemplarAnaliseEspecieService {

    @Autowired
    private ExemplarAnaliseEspecieRepository exemplarAnaliseEspecieRepository;
    @Autowired
    private ExemplarAnaliseDataValidator dataValidator;

    public List<ExemplarAnaliseEspecie> listAll() {
        return StreamSupport.stream(
                exemplarAnaliseEspecieRepository.findAll().spliterator(),
                false)
                .collect(Collectors.toList());
    }

    public ExemplarAnaliseEspecieResponse save(ExemplarAnaliseEspecieVo vo) throws ExemplarAnaliseApiException {
        dataValidator.validateAnalise(vo.getAnaliseId());
        dataValidator.validateEspecie(vo.getEspecieId());
        dataValidator.validateExemplar(vo.getExemplarId());
        ExemplarAnaliseEspecie exemplarAnaliseEspecie = exemplarAnaliseEspecieRepository.save(buildExemplarAnaliseEspecie(vo));
        return buildExemplarAnaliseEspecieResponse(exemplarAnaliseEspecie);
    }

    private ExemplarAnaliseEspecieResponse buildExemplarAnaliseEspecieResponse(ExemplarAnaliseEspecie exemplarAnaliseEspecie) {
        return ExemplarAnaliseEspecieResponse.builder()
                .analiseId(exemplarAnaliseEspecie.getId().getAnalise().getId())
                .especieId(exemplarAnaliseEspecie.getId().getEspecie().getId())
                .exemplarId(exemplarAnaliseEspecie.getId().getExemplar().getId())
                .quantidade(exemplarAnaliseEspecie.getQuantidade())
                .build();
    }

    private ExemplarAnaliseEspecie buildExemplarAnaliseEspecie(ExemplarAnaliseEspecieVo vo) {
        ExemplarAnaliseEspecieId id = buildExemplarAnaliseEspecieId(vo);
        return ExemplarAnaliseEspecie.builder()
                .id(id)
                .quantidade(vo.getQuantidade())
                .build();
    }

    private ExemplarAnaliseEspecieId buildExemplarAnaliseEspecieId(ExemplarAnaliseEspecieVo vo) {
        Analise analise = buildAnalise(vo);
        Especie especie = buildEspecie(vo);
        Exemplar exemplar = buildExemplar(vo);
        return ExemplarAnaliseEspecieId.builder()
                .analise(analise)
                .especie(especie)
                .exemplar(exemplar)
                .build();
    }

    private Exemplar buildExemplar(ExemplarAnaliseEspecieVo vo) {
        return Exemplar.builder()
                .id(vo.getExemplarId())
                .build();
    }

    private Especie buildEspecie(ExemplarAnaliseEspecieVo vo) {
        return Especie.builder()
                .id(vo.getEspecieId())
                .build();
    }

    private Analise buildAnalise(ExemplarAnaliseEspecieVo vo) {
        return Analise.builder()
                .id(vo.getAnaliseId())
                .build();
    }
}
