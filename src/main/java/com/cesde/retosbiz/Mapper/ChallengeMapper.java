package com.cesde.retosbiz.Mapper;

import java.util.List;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

import com.cesde.retosbiz.Dto.ChallengeDto;
import com.cesde.retosbiz.Dto.UpdateChallengeDto;
import com.cesde.retosbiz.Model.ChallengeModel;

@Mapper(componentModel = "spring")
public interface ChallengeMapper {
    // Modelo -> DTO: los ids de las relaciones se sacan de los objetos relacionados
    @Mapping(source = "nombre", target = "name")
    @Mapping(source = "descripcion", target = "description")
    @Mapping(source = "fechaInicio", target = "startDate")
    @Mapping(source = "fechaFin", target = "endDate")
    @Mapping(source = "estado", target = "status")
    @Mapping(source = "empresa.id", target = "companyId")
    @Mapping(source = "categoria.id", target = "categoryId")
    @Mapping(source = "prioridad.id", target = "priorityId")
    ChallengeDto toChallengeDto(ChallengeModel challengeModel);

    List<ChallengeDto> toChallengeDto(Iterable<ChallengeModel> challengeModels);

    // DTO -> Modelo: las relaciones NO se arman aquí, las asigna el repositorio buscándolas por id
    @Mapping(source = "name", target = "nombre")
    @Mapping(source = "description", target = "descripcion")
    @Mapping(source = "startDate", target = "fechaInicio")
    @Mapping(source = "endDate", target = "fechaFin")
    @Mapping(source = "status", target = "estado")
    @Mapping(target = "empresa", ignore = true)
    @Mapping(target = "categoria", ignore = true)
    @Mapping(target = "prioridad", ignore = true)
    ChallengeModel toChallengeModel(ChallengeDto challengeDto);

    @Mapping(source = "name", target = "nombre")
    @Mapping(source = "description", target = "descripcion")
    @Mapping(source = "startDate", target = "fechaInicio")
    @Mapping(source = "endDate", target = "fechaFin")
    @Mapping(source = "status", target = "estado")
    @Mapping(target = "id", ignore = true)
    @Mapping(target = "empresa", ignore = true)
    @Mapping(target = "categoria", ignore = true)
    @Mapping(target = "prioridad", ignore = true)
    void updateChallengeModelFromDto(UpdateChallengeDto updateChallengeDto,
        @MappingTarget ChallengeModel challengeModel);
}