package com.sce.api.deposito.vistoria.model;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class DepositoVistoriaVo {

    private Long depositoId;
    private Long vistoriaId;
}
