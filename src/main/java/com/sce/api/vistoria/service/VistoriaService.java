package com.sce.api.vistoria.service;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sce.api.vistoria.exception.VistoriaApiException;
import com.sce.api.vistoria.vo.VistoriaResponse;
import com.sce.api.vistoria.vo.VistoriaVo;
import com.sce.persistence.imovel.entity.Imovel;
import com.sce.persistence.usuario.entity.Usuario;
import com.sce.persistence.vistoria.entity.Vistoria;
import com.sce.persistence.vistoria.repository.VistoriaRepository;

@Service
public class VistoriaService {

    @Autowired
    private VistoriaRepository vistoriaRepository;
    @Autowired
    private VistoriaDataValidator dataValidator;

    public List<Vistoria> listAll() {
        return StreamSupport.stream(vistoriaRepository.findAll().spliterator(), false).collect(Collectors.toList());
    }

    public VistoriaResponse save(VistoriaVo vo) throws VistoriaApiException {
        dataValidator.validateImovel(vo.getImovelId());
        dataValidator.validateUsuario(vo.getUsuarioId());
        Vistoria vistoria = vistoriaRepository.save(buildVistoria(vo));
        return buildVistoriaResponse(vistoria);
    }

    private Vistoria buildVistoria(VistoriaVo vo) {
        Usuario usuario = buildUsuario(vo);
        Imovel imovel = buildImovel(vo);
        return Vistoria.builder()
                .codigoAtividade(vo.getCodigoAtividade())
                .concluida(vo.isConcluida())
                .dataVistoria(vo.getDataVistoria())
                .imovelFechado(vo.isImovelFechado())
                .recusada(vo.isRecusada())
                .tipoVisita(vo.getTipoVisita())
                .tipo(vo.getTipo())
                .usuario(usuario)
                .imovel(imovel)
                .build();
    }

    private Imovel buildImovel(VistoriaVo vo) {
        return Imovel.builder()
                .id(vo.getImovelId())
                .build();
    }

    private Usuario buildUsuario(VistoriaVo vo) {
        return Usuario.builder()
                .id(vo.getUsuarioId())
                .build();
    }

    private VistoriaResponse buildVistoriaResponse(Vistoria vistoria) {
        return VistoriaResponse.builder()
                .codigoAtividade(vistoria.getCodigoAtividade())
                .concluida(vistoria.isConcluida())
                .dataVistoria(vistoria.getDataVistoria())
                .imovelFechado(vistoria.isImovelFechado())
                .recusada(vistoria.isRecusada())
                .usuarioId(vistoria.getUsuario().getId())
                .imovelId(vistoria.getImovel().getId())
                .tipoVisita(vistoria.getTipoVisita())
                .tipo(vistoria.getTipo())
                .id(vistoria.getId())
                .build();
    }
}
