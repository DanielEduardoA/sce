package com.sce.api.analise.model;

import java.util.Date;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class AnaliseVo {

    private Date dataEntrada;
    private Date dataConclusao;
    private String laboratorio;
    private String laboratorista;

}
