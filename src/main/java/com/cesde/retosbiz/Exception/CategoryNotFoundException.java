package com.cesde.retosbiz.Exception;

public class CategoryNotFoundException extends RuntimeException {
	public CategoryNotFoundException(String id) {
		super("La categoría con el id \"" + id + "\" no existe.");
	}

}