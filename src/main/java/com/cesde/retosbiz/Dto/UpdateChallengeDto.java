package com.cesde.retosbiz.Dto;

import java.time.LocalDate;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public record UpdateChallengeDto(
        @NotBlank(message = "El nombre es obligatorio")
        @Size(max = 150, message = "El nombre no puede exceder los 150 caracteres")
        String name,
        @Size(max = 500, message = "La descripción no puede exceder los 500 caracteres")
        String description,
        LocalDate startDate,
        LocalDate endDate,
        @NotBlank(message = "El estado es obligatorio")
        @Size(max = 50, message = "El estado no puede exceder los 50 caracteres")
        String status,
        @NotBlank(message = "La empresa es obligatoria")
        @Size(max = 50, message = "El ID de empresa no puede exceder los 50 caracteres")
        String companyId,
        @NotBlank(message = "La categoría es obligatoria")
        @Size(max = 50, message = "El ID de categoría no puede exceder los 50 caracteres")
        String categoryId,
        @NotBlank(message = "La prioridad es obligatoria")
        @Size(max = 50, message = "El ID de prioridad no puede exceder los 50 caracteres")
        String priorityId) {
}