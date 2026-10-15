package com.cesde.retosbiz.Exception;

public class CategoryAlreadyExistException extends RuntimeException {
	public CategoryAlreadyExistException(String categoryName) {
		super("La categoría \"" + categoryName + "\" ya existe");
	}
	
}
