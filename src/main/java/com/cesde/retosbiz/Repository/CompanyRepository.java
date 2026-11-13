package com.cesde.retosbiz.Repository;

import java.util.List;

import com.cesde.retosbiz.Dto.CompanyDto;
import com.cesde.retosbiz.Dto.UpdateCompanyDto;

public interface CompanyRepository {
    List<CompanyDto> getAllCompanies();

    CompanyDto getCompanyById(String id);

    CompanyDto createCompany(CompanyDto companyDto);

    CompanyDto updateCompany(String id, UpdateCompanyDto updateCompanyDto);

    void deleteCompany(String id);

    boolean existsByNit(String nit);

    boolean existsById(String id);
}