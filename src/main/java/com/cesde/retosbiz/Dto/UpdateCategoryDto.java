package com.cesde.retosbiz.Dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public record UpdateCategoryDto(
		@NotBlank(message = "El título es obligatorio") String name,
		@Size(max = 200, message = "La descripción no puede exceder los 200 caracteres") String description,
		@NotBlank(message = "El área responsable es obligatoria") @Size(min = 5, max = 100, message = "El área responsable debe tener entre 5 y 100 caracteres") String responsible_area) {
}