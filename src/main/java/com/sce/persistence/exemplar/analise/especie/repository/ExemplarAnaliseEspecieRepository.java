package com.sce.persistence.exemplar.analise.especie.repository;

import org.springframework.data.repository.CrudRepository;

import com.sce.persistence.exemplar.analise.especie.entity.ExemplarAnaliseEspecie;
import com.sce.persistence.exemplar.analise.especie.entity.ExemplarAnaliseEspecieId;

public interface ExemplarAnaliseEspecieRepository extends CrudRepository<ExemplarAnaliseEspecie, ExemplarAnaliseEspecieId> {

}
