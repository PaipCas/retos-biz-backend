package com.cesde.retosbiz.Mapper;

import java.util.List;

import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

import com.cesde.retosbiz.Dto.ChallengeDto;
import com.cesde.retosbiz.Dto.UpdateChallengeDto;
import com.cesde.retosbiz.Model.ChallengeModel;

@Mapper(componentModel = "spring")
public interface ChallengeMapper {
    @Mapping(source = "nombre", target = "name")
    @Mapping(source = "descripcion", target = "description")
    @Mapping(source = "fechaInicio", target = "startDate")
    @Mapping(source = "fechaFin", target = "endDate")
    @Mapping(source = "estado", target = "status")
    @Mapping(source = "idEmpresa", target = "companyId")
    ChallengeDto toChallengeDto(ChallengeModel challengeModel);

    List<ChallengeDto> toChallengeDto(Iterable<ChallengeModel> challengeModels);

    @InheritInverseConfiguration
    ChallengeModel toChallengeModel(ChallengeDto challengeDto);

    @Mapping(source = "name", target = "nombre")
    @Mapping(source = "description", target = "descripcion")
    @Mapping(source = "startDate", target = "fechaInicio")
    @Mapping(source = "endDate", target = "fechaFin")
    @Mapping(source = "status", target = "estado")
    @Mapping(source = "companyId", target = "idEmpresa")
    @Mapping(target = "id", ignore = true)
    void updateChallengeModelFromDto(UpdateChallengeDto updateChallengeDto,
        @MappingTarget ChallengeModel challengeModel);
}
