package com.sce.api.tratamento.model;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class TratamentoResponse {
    
    private long id;
    private long depositoId;
    private long vistoriaId;
    private int quantidadeDepositosEliminados;
    private String tipoLarvicida1;
    private int quantidadeLarvicida1;
    private String tipoLarvicida2;
    private int quantidadeLarvicida2;
    private String tipoAdulticida;
    private int quantidadeCargasAdulticida;

}
