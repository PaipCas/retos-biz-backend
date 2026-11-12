package com.cesde.retosbiz.Exception;

public class CompanyNotFoundException extends RuntimeException {
	public CompanyNotFoundException(String id) {
		super("La empresa con el id \"" + id + "\" no existe.");
	}

}
