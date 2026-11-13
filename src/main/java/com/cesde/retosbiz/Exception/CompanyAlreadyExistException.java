package com.cesde.retosbiz.Exception;

public class CompanyAlreadyExistException extends RuntimeException {
    public CompanyAlreadyExistException(String nit) {
        super("La empresa con el NIT \"" + nit + "\" ya existe");
    }

}