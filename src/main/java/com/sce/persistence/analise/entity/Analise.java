package com.sce.persistence.analise.entity;

import java.util.Date;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.sce.persistence.amostra.entity.Amostra;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Table(name = "analise")
@Entity
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Analise {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(columnDefinition = "DATE")
    private Date dataEntrada;
    
    @Column(columnDefinition = "DATE")
    private Date dataConclusao;

    @Column(length = 255, nullable = false)
    private String laboratorio;
    
    @Column(length = 255, nullable = false)
    private String laboratorista;

    @OneToMany(mappedBy = "analise")
    private Set<Amostra> amostras;
}
