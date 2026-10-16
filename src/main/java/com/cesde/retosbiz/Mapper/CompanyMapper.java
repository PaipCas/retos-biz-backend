package com.cesde.retosbiz.Mapper;

import com.cesde.retosbiz.Dto.CompanyDto;
import com.cesde.retosbiz.Model.CompanyModel;


public interface CompanyMapper {

    CompanyModel toCompanyModel(CompanyDto companyDto);

    CompanyDto toCompanyDto(CompanyModel companyModel);

	
}