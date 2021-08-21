package com.sce.api.tratamento.model;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class TratamentoVo {
    
    private Long depositoId;
    private Long vistoriaId;
    private int quantidadeDepositosEliminados;
    private String tipoLarvicida1;
    private int quantidadeLarvicida1;
    private String tipoLarvicida2;
    private int quantidadeLarvicida2;
    private String tipoAdulticida;
    private int quantidadeCargasAdulticida;

}
