package com.sce.api.exemplar.analise.model;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Builder
@Getter
@Setter
public class ExemplarAnaliseEspecieVo {

    private Long exemplarId;
    private Long especieId;
    private Long analiseId;
    private int quantidade;
}
