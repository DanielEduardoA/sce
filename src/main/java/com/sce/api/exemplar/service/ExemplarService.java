package com.sce.api.exemplar.service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sce.api.exemplar.model.ExemplarResponse;
import com.sce.persistence.exemplar.entity.Exemplar;
import com.sce.persistence.exemplar.repository.ExemplarRepository;

@Service
public class ExemplarService {
	@Autowired
	private ExemplarRepository exemplarRepository;
	
	public List<ExemplarResponse> listAll() {
        List<ExemplarResponse> response = new ArrayList<>();
        List<Exemplar> exemplares = StreamSupport.stream(exemplarRepository.findAll().spliterator(), false).collect(Collectors.toList());
        exemplares.stream().forEach(exemplar -> response.add(buildExemplarResponse(exemplar)));
        return response;
    }

    private ExemplarResponse buildExemplarResponse(Exemplar exemplar) {
        return ExemplarResponse.builder()
                .id(exemplar.getId())
                .nome(exemplar.getNome())
                .build();
    }
	
	
}
