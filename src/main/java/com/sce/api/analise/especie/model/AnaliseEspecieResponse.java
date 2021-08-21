package com.sce.api.analise.especie.model;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class AnaliseEspecieResponse {

    private Long especieId;
    private Long analiseId;
}
