package com.sce.api.imovel.service;

import static org.springframework.http.HttpStatus.NOT_FOUND;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sce.api.imovel.exception.ImovelApiException;
import com.sce.api.imovel.model.ImovelResponse;
import com.sce.api.imovel.model.ImovelVo;
import com.sce.persistence.imovel.entity.Imovel;
import com.sce.persistence.imovel.repository.ImovelRepository;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class ImovelService {

    @Autowired
    private ImovelRepository imovelRepository;

    public List<Imovel> listAll() {
        return StreamSupport.stream(imovelRepository.findAll().spliterator(), false).collect(Collectors.toList());
    }

    public ImovelResponse save(ImovelVo vo) {
        Imovel imovel = imovelRepository.save(buildImovel(vo));
        return buildImovelResponse(imovel);
    }

    public ImovelResponse update(ImovelVo vo, Long id) throws ImovelApiException {
        Optional<Imovel> imovelOpt = imovelRepository.findById(id);
        if (imovelOpt.isPresent()) {
            Imovel imovel = imovelRepository.save(buildImovel(vo, id));
            return buildImovelResponse(imovel);
        } else {
            log.warn("Imóvel não encontrado.");
            throw new ImovelApiException("Imóvel não encontrado", NOT_FOUND);
        }

    }

    public ImovelResponse getById(Long id) throws ImovelApiException {
        Optional<Imovel> imovelOpt = imovelRepository.findById(id);
        if (imovelOpt.isPresent()) {
            return buildImovelResponse(imovelOpt.get());
        } else {
            log.warn("Imóvel não encontrado.");
            throw new ImovelApiException("Imóvel não encontrado", NOT_FOUND);
        }
    }

    private Imovel buildImovel(ImovelVo vo) {
        return Imovel.builder()
                .bairro(vo.getBairro())
                .cidade(vo.getCidade())
                .complemento(vo.getComplemento())
                .lado(vo.getLado())
                .numero(vo.getNumero())
                .numeroQuarteirao(vo.getNumeroQuarteirao())
                .rua(vo.getRua())
                .tipo(vo.getTipo())
                .zona(vo.getZona())
                .build();
    }

    private Imovel buildImovel(ImovelVo vo, long id) {
        return Imovel.builder()
                .bairro(vo.getBairro())
                .cidade(vo.getCidade())
                .complemento(vo.getComplemento())
                .lado(vo.getLado())
                .numero(vo.getNumero())
                .numeroQuarteirao(vo.getNumeroQuarteirao())
                .rua(vo.getRua())
                .tipo(vo.getTipo())
                .zona(vo.getZona())
                .id(id)
                .build();
    }

    private ImovelResponse buildImovelResponse(Imovel imovel) {
        return ImovelResponse.builder()
                .bairro(imovel.getBairro())
                .cidade(imovel.getCidade())
                .complemento(imovel.getComplemento())
                .lado(imovel.getLado())
                .numero(imovel.getNumero())
                .numeroQuarteirao(imovel.getNumeroQuarteirao())
                .rua(imovel.getRua())
                .tipo(imovel.getTipo())
                .zona(imovel.getZona())
                .id(imovel.getId())
                .build();
    }
}