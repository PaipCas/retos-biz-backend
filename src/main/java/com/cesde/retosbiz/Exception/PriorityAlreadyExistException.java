package com.cesde.retosbiz.Exception;

public class PriorityAlreadyExistException extends RuntimeException {
	public PriorityAlreadyExistException(String priorityName) {
		super("La prioridad \"" + priorityName + "\" ya existe");
	}
}