package com.sce.api.tratamento.service;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sce.api.tratamento.exception.copy.TratamentoApiException;
import com.sce.api.tratamento.model.TratamentoResponse;
import com.sce.api.tratamento.model.TratamentoVo;
import com.sce.persistence.deposito.entity.Deposito;
import com.sce.persistence.deposito.vistoria.entity.DepositoVistoria;
import com.sce.persistence.deposito.vistoria.entity.DepositoVistoriaId;
import com.sce.persistence.tratamento.entity.Tratamento;
import com.sce.persistence.tratamento.repository.TratamentoRepository;
import com.sce.persistence.vistoria.entity.Vistoria;

@Service
public class TratamentoService {

    @Autowired
    private TratamentoRepository tratamentoRepository;
    @Autowired
    private TratamentoDataValidator dataValidator;

    public List<Tratamento> listAll() {
        return StreamSupport.stream(tratamentoRepository.findAll().spliterator(), false).collect(Collectors.toList());
    }

    public TratamentoResponse save(TratamentoVo vo) throws TratamentoApiException {
        dataValidator.validateDeposito(vo.getDepositoId());
        dataValidator.validateVistoria(vo.getVistoriaId());
        Tratamento tratamento = tratamentoRepository.save(buildTratamento(vo));
        return buildTratamentoResponse(tratamento);
    }

    private TratamentoResponse buildTratamentoResponse(Tratamento tratamento) {
        return TratamentoResponse.builder()
                .depositoId(tratamento.getDepositoVistoria().getId().getDeposito().getId())
                .quantidadeCargasAdulticida(tratamento.getQuantidadeCargasAdulticida())
                .quantidadeDepositosEliminados(tratamento.getQuantidadeDepositosEliminados())
                .quantidadeLarvicida1(tratamento.getQuantidadeLarvicida1())
                .quantidadeLarvicida2(tratamento.getQuantidadeLarvicida2())
                .tipoAdulticida(tratamento.getTipoAdulticida())
                .tipoLarvicida1(tratamento.getTipoLarvicida1())
                .tipoLarvicida2(tratamento.getTipoLarvicida2())
                .id(tratamento.getId())
                .vistoriaId(tratamento.getDepositoVistoria().getId().getVistoria().getId())
                .build();
    }

    private Tratamento buildTratamento(TratamentoVo vo) {
        DepositoVistoria depositoVistoria = buildDepositoVistoria(vo);
        return Tratamento.builder()
                .quantidadeCargasAdulticida(vo.getQuantidadeCargasAdulticida())
                .quantidadeDepositosEliminados(vo.getQuantidadeDepositosEliminados())
                .quantidadeLarvicida1(vo.getQuantidadeLarvicida1())
                .quantidadeLarvicida2(vo.getQuantidadeLarvicida2())
                .tipoAdulticida(vo.getTipoAdulticida())
                .tipoLarvicida1(vo.getTipoLarvicida1())
                .tipoLarvicida2(vo.getTipoLarvicida2())
                .depositoVistoria(depositoVistoria)
                .build();
    }

    private DepositoVistoria buildDepositoVistoria(TratamentoVo vo) {
        DepositoVistoriaId id = buildDepositoVistoriaId(vo);
        return DepositoVistoria.builder()
                .id(id)
                .build();
    }

    private DepositoVistoriaId buildDepositoVistoriaId(TratamentoVo vo) {
        Deposito deposito = buildDeposito(vo);
        Vistoria vistoria = buildVistoria(vo);
        return DepositoVistoriaId.builder()
                .deposito(deposito)
                .vistoria(vistoria)
                .build();
    }

    private Vistoria buildVistoria(TratamentoVo vo) {
        return Vistoria.builder()
                .id(vo.getVistoriaId())
                .build();
    }

    private Deposito buildDeposito(TratamentoVo vo) {
        return Deposito.builder()
                .id(vo.getDepositoId())
                .build();
    }
}
