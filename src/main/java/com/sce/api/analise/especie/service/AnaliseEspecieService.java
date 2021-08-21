package com.sce.api.analise.especie.service;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sce.api.analise.especie.exception.AnaliseEspecieApiException;
import com.sce.api.analise.especie.model.AnaliseEspecieResponse;
import com.sce.api.analise.especie.model.AnaliseEspecieVo;
import com.sce.persistence.analise.especie.entity.AnaliseEspecie;
import com.sce.persistence.analise.especie.entity.AnaliseEspecieId;
import com.sce.persistence.analise.especie.repository.AnaliseEspecieRepository;

@Service
public class AnaliseEspecieService {

    @Autowired
    private AnaliseEspecieRepository analiseEspecieRepository;
    @Autowired
    private AnaliseEspecieDataValidator dataValidator;

    public List<AnaliseEspecie> listAll() {
        return StreamSupport.stream(
                analiseEspecieRepository.findAll().spliterator(),
                false)
                .collect(Collectors.toList());
    }

    public AnaliseEspecieResponse save(AnaliseEspecieVo vo) throws AnaliseEspecieApiException {
        dataValidator.validateAnalise(vo.getAnaliseId());
        dataValidator.validateEspecie(vo.getEspecieId());
        AnaliseEspecie analiseEspecie = analiseEspecieRepository.save(buildAnaliseEspecie(vo));
        return buildAnaliseEspecieResponse(analiseEspecie);
    }

    private AnaliseEspecieResponse buildAnaliseEspecieResponse(AnaliseEspecie analiseEspecie) {
        return AnaliseEspecieResponse.builder()
                .analiseId(analiseEspecie.getId().getAnaliseId())
                .especieId(analiseEspecie.getId().getEspecieId())
                .build();
    }

    private AnaliseEspecie buildAnaliseEspecie(AnaliseEspecieVo vo) {
        AnaliseEspecieId id = buildAnaliseEspecieId(vo);
        return AnaliseEspecie.builder()
                .id(id).build();

    }

    private AnaliseEspecieId buildAnaliseEspecieId(AnaliseEspecieVo vo) {
        return AnaliseEspecieId.builder()
                .analiseId(vo.getAnaliseId())
                .especieId(vo.getEspecieId())
                .build();
    }
}
