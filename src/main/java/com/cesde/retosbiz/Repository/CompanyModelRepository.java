package com.cesde.retosbiz.Repository;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.cesde.retosbiz.Crud.CompanyCrud;
import com.cesde.retosbiz.Dto.CompanyDto;
import com.cesde.retosbiz.Dto.UpdateCompanyDto;
import com.cesde.retosbiz.Mapper.CompanyMapper;
import com.cesde.retosbiz.Model.CompanyModel;

@Repository
public class CompanyModelRepository implements CompanyRepository {
    private final CompanyCrud companyCrud;
    private final CompanyMapper companyMapper;

    public CompanyModelRepository(CompanyCrud companyCrud, CompanyMapper companyMapper) {
        this.companyCrud = companyCrud;
        this.companyMapper = companyMapper;
    }

    @Override
    public List<CompanyDto> getAllCompanies() {
        return this.companyMapper.toCompanyDto(this.companyCrud.findAll());
    }

    @Override
    public CompanyDto getCompanyById(String id) {
        return this.companyCrud.findById(id).map(this.companyMapper::toCompanyDto).orElse(null);
    }

    @Override
    public CompanyDto createCompany(CompanyDto companyDto) {
        CompanyModel companyModel = this.companyMapper.toCompanyModel(companyDto);
        return this.companyMapper.toCompanyDto(this.companyCrud.save(companyModel));
    }

    @Override
    public CompanyDto updateCompany(String id, UpdateCompanyDto updateCompanyDto) {
        CompanyModel companyModel = this.companyCrud.findById(id).orElseThrow();
        this.companyMapper.updateCompanyModelFromDto(updateCompanyDto, companyModel);

        return this.companyMapper.toCompanyDto(this.companyCrud.save(companyModel));
    }

    @Override
    public boolean existsByNit(String nit) {
        return this.companyCrud.existsByNit(nit);
    }

    @Override
    public boolean existsById(String id) {
        return this.companyCrud.existsById(id);
    }

    @Override
    public void deleteCompany(String id) {
        this.companyCrud.deleteById(id);
    }
}