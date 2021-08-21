package com.sce.api.vistoria.vo;

import java.util.Date;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class VistoriaVo {

    private boolean concluida;
    private boolean imovelFechado;
    private boolean recusada;
    private Date dataVistoria;
    private String codigoAtividade;
    private String tipoVisita;
    private int tipo;
    private long usuarioId;
    private long imovelId;
}
