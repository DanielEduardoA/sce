package com.sce.persistence.analise.especie.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Embeddable;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Embeddable
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class AnaliseEspecieId implements Serializable {

	private static final long serialVersionUID = 1504161552670967690L;

	@Column(name = "especie_id_fk")
    private long especieId;
	
	@Column(name = "analise_id_fk")
    private long analiseId;
}
