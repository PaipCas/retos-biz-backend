package com.cesde.retosbiz.Mapper;

import java.util.List;

import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

import com.cesde.retosbiz.Dto.CategoryDto;
import com.cesde.retosbiz.Dto.UpdateCategoryDto;
import com.cesde.retosbiz.Model.CategoryModel;

@Mapper(componentModel = "spring")
public interface CategoryMapper {
	@Mapping(source = "nombre", target = "name")
	@Mapping(source = "descripcion", target = "description")
	@Mapping(source = "area_responsable", target = "responsible_area")
	CategoryDto toCategoryDto(CategoryModel categoryModel);
	List<CategoryDto> toCategoryDto(Iterable<CategoryModel> categoryModels);

	@InheritInverseConfiguration
	CategoryModel toCategoryModel(CategoryDto categoryDto);

	@Mapping(source = "name", target = "nombre")
	@Mapping(source = "description", target = "descripcion")
	@Mapping(source = "responsible_area", target = "area_responsable")
	void updateCategoryModelFromDto(UpdateCategoryDto updateCategoryDto, @MappingTarget CategoryModel categoryModel);
}