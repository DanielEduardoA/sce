package com.sce.api.deposito.service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sce.api.deposito.model.DepositoResponse;
import com.sce.persistence.deposito.entity.Deposito;
import com.sce.persistence.deposito.repository.DepositoRepository;

@Service
public class DepositoService {

    @Autowired
    private DepositoRepository depositoRepository;

    public List<DepositoResponse> listAll() {
        List<DepositoResponse> response = new ArrayList<>();
        List<Deposito> depositos = StreamSupport.stream(depositoRepository.findAll().spliterator(), false).collect(Collectors.toList());
        depositos.stream().forEach(deposito -> response.add(buildDepositoResponse(deposito)));
        return response;
    }

    private DepositoResponse buildDepositoResponse(Deposito deposito) {
        return DepositoResponse.builder()
                .id(deposito.getId())
                .nome(deposito.getNome())
                .codigo(deposito.getCodigo())
                .build();
    }
}
