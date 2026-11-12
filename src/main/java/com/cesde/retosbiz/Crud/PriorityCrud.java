package com.cesde.retosbiz.Crud;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import com.cesde.retosbiz.Model.PriorityModel;

public interface PriorityCrud extends CrudRepository<PriorityModel, String> {
	PriorityModel findFirstByNombre(String nombre);

	List<PriorityModel> findByNivel(Integer nivel);
}