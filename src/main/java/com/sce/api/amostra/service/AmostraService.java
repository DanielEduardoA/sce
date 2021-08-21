package com.sce.api.amostra.service;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sce.api.amostra.exception.AmostraApiException;
import com.sce.api.amostra.model.AmostraResponse;
import com.sce.api.amostra.model.AmostraVo;
import com.sce.persistence.amostra.entity.Amostra;
import com.sce.persistence.amostra.repository.AmostraRepository;
import com.sce.persistence.analise.entity.Analise;
import com.sce.persistence.deposito.entity.Deposito;
import com.sce.persistence.deposito.vistoria.entity.DepositoVistoria;
import com.sce.persistence.deposito.vistoria.entity.DepositoVistoriaId;
import com.sce.persistence.vistoria.entity.Vistoria;

@Service
public class AmostraService {

    @Autowired
    private AmostraRepository amostraRepository;
    @Autowired
    private AmostraDataValidator dataValidator;

    public List<Amostra> listAll() {
        return StreamSupport.stream(
                amostraRepository.findAll().spliterator(),
                false)
                .collect(Collectors.toList());
    }

    public AmostraResponse save(AmostraVo vo) throws AmostraApiException {
        dataValidator.validateAnalise(vo.getAnaliseId());
        dataValidator.validateDeposito(vo.getDepositoId());
        dataValidator.validateVistoria(vo.getVistoriaId());
        Amostra amostra = amostraRepository.save(buildAmostra(vo));
        return buildAmostraResponse(amostra);
    }

    private AmostraResponse buildAmostraResponse(Amostra amostra) {
        return AmostraResponse.builder()
                .analiseId(amostra.getAnalise().getId())
                .depositoId(amostra.getDepositoVistoria().getId().getDeposito().getId())
                .quantidadeTubitos(amostra.getQuantidadeTubitos())
                .vistoriaId(amostra.getDepositoVistoria().getId().getVistoria().getId())
                .tipo(amostra.getTipo())
                .id(amostra.getId())
                .build();
    }

    private Amostra buildAmostra(AmostraVo vo) {
        Analise analise = buildAnalise(vo);
        DepositoVistoria depositoVistoria = buildDepositoVistoria(vo);
        return Amostra.builder()
                .analise(analise)
                .depositoVistoria(depositoVistoria)
                .tipo(vo.getTipo())
                .quantidadeTubitos(vo.getQuantidadeTubitos())
                .build();
    }

    private DepositoVistoria buildDepositoVistoria(AmostraVo vo) {
        DepositoVistoriaId id = buildDepositoVistoriaId(vo);
        return DepositoVistoria.builder()
                .id(id)
                .build();
    }

    private DepositoVistoriaId buildDepositoVistoriaId(AmostraVo vo) {
        Deposito deposito = buildDeposito(vo);
        Vistoria vistoria = buildVistoria(vo);
        return DepositoVistoriaId.builder()
                .deposito(deposito)
                .vistoria(vistoria)
                .build();
    }

    private Vistoria buildVistoria(AmostraVo vo) {
        return Vistoria.builder()
                .id(vo.getVistoriaId())
                .build();
    }

    private Deposito buildDeposito(AmostraVo vo) {
        return Deposito.builder()
                .id(vo.getDepositoId())
                .build();
    }

    private Analise buildAnalise(AmostraVo vo) {
        return Analise.builder()
                .id(vo.getAnaliseId())
                .build();

    }
}
