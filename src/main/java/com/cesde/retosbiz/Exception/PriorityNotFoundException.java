package com.cesde.retosbiz.Exception;

public class PriorityNotFoundException extends RuntimeException {
	public PriorityNotFoundException(String id) {
		super("La prioridad con el id \"" + id + "\" no existe.");
	}

}