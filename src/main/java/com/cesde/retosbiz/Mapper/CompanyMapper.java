package com.cesde.retosbiz.Mapper;

import java.util.List;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

import com.cesde.retosbiz.Dto.CompanyDto;
import com.cesde.retosbiz.Dto.UpdateCompanyDto;
import com.cesde.retosbiz.Model.CompanyModel;

@Mapper(componentModel = "spring")
public interface CompanyMapper {
    CompanyDto toCompanyDto(CompanyModel companyModel);

    List<CompanyDto> toCompanyDto(Iterable<CompanyModel> companyModels);

    @Mapping(target = "id", ignore = true)
    CompanyModel toCompanyModel(CompanyDto companyDto);

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "nit", ignore = true)
    void updateCompanyModelFromDto(UpdateCompanyDto updateCompanyDto, @MappingTarget CompanyModel companyModel);
}