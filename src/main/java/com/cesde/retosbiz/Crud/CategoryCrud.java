package com.cesde.retosbiz.Crud;

import org.springframework.data.repository.CrudRepository;

import com.cesde.retosbiz.Model.CategoryModel;

public interface CategoryCrud extends CrudRepository<CategoryModel, String> {
	CategoryModel findFirstByNombre(String nombre);
}