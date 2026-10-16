package com.cesde.retosbiz.Crud;

import org.springframework.data.repository.CrudRepository;

import com.cesde.retosbiz.Model.CompanyModel;

public interface CompanyCrud extends CrudRepository<CompanyModel, String> {
}