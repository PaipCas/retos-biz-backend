package com.cesde.retosbiz.Mapper;

import java.util.List;

import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

import com.cesde.retosbiz.Dto.PriorityDto;
import com.cesde.retosbiz.Dto.UpdatePriorityDto;
import com.cesde.retosbiz.Model.PriorityModel;

@Mapper(componentModel = "spring")
public interface PriorityMapper {
	@Mapping(source = "nombre", target = "name")
	@Mapping(source = "nivel", target = "level")
	@Mapping(source = "dias_max_respuesta", target = "max_response_days")
	PriorityDto toPriorityDto(PriorityModel priorityModel);

	List<PriorityDto> toPriorityDto(Iterable<PriorityModel> priorityModels);

	@InheritInverseConfiguration
	PriorityModel toPriorityModel(PriorityDto priorityDto);

	@Mapping(source = "name", target = "nombre")
	@Mapping(source = "level", target = "nivel")
	@Mapping(source = "max_response_days", target = "dias_max_respuesta")
	void updatePriorityModelFromDto(UpdatePriorityDto updatePriorityDto, @MappingTarget PriorityModel priorityModel);
}