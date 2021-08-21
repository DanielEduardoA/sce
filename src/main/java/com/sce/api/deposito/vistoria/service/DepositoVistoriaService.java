package com.sce.api.deposito.vistoria.service;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sce.api.deposito.vistoria.exception.DepositoVistoriaApiException;
import com.sce.api.deposito.vistoria.model.DepositoVistoriaResponse;
import com.sce.api.deposito.vistoria.model.DepositoVistoriaVo;
import com.sce.persistence.deposito.entity.Deposito;
import com.sce.persistence.deposito.vistoria.entity.DepositoVistoria;
import com.sce.persistence.deposito.vistoria.entity.DepositoVistoriaId;
import com.sce.persistence.deposito.vistoria.repository.DepositoVistoriaRepository;
import com.sce.persistence.vistoria.entity.Vistoria;

@Service
public class DepositoVistoriaService {

    @Autowired
    private DepositoVistoriaRepository depositoVistoriaRepository;
    @Autowired
    private DepositoVistoriaDataValidator dataValidator;

    public List<DepositoVistoria> listAll() {
        return StreamSupport.stream(
                depositoVistoriaRepository.findAll().spliterator(),
                false)
                .collect(Collectors.toList());
    }

    public DepositoVistoriaResponse save(DepositoVistoriaVo vo) throws DepositoVistoriaApiException {
        dataValidator.validateDeposito(vo.getDepositoId());
        dataValidator.validateVistoria(vo.getVistoriaId());
        DepositoVistoria depositoVistoria = depositoVistoriaRepository.save(buildDepositoVistoria(vo));
        return buildlDepositoVistoriaResponse(depositoVistoria);
    }

    private DepositoVistoria buildDepositoVistoria(DepositoVistoriaVo vo) {
        DepositoVistoriaId id = buildDepositoVistoriaId(vo);
        return DepositoVistoria.builder()
                .id(id)
                .build();
    }

    private DepositoVistoriaId buildDepositoVistoriaId(DepositoVistoriaVo vo) {
        Deposito deposito = buildDeposito(vo);
        Vistoria vistoria = buildVistoria(vo);
        return DepositoVistoriaId.builder()
                .deposito(deposito)
                .vistoria(vistoria)
                .build();
    }

    private Vistoria buildVistoria(DepositoVistoriaVo vo) {
        return Vistoria.builder()
                .id(vo.getVistoriaId())
                .build();
    }

    private Deposito buildDeposito(DepositoVistoriaVo vo) {
        return Deposito.builder()
                .id(vo.getDepositoId())
                .build();
    }

    private DepositoVistoriaResponse buildlDepositoVistoriaResponse(DepositoVistoria depositoVistoria) {
        return DepositoVistoriaResponse.builder()
                .depositoId(depositoVistoria.getId().getDeposito().getId())
                .vistoriaId(depositoVistoria.getId().getVistoria().getId())
                .build();
    }
}
