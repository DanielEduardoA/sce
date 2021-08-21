package com.sce.api.exemplar.analise.model;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Builder
@Getter
@Setter
public class ExemplarAnaliseEspecieResponse {

    private long id;
    private long exemplarId;
    private long especieId;
    private long analiseId;
    private int quantidade;
}
