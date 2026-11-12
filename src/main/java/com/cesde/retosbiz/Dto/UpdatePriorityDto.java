package com.cesde.retosbiz.Dto;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;

public record UpdatePriorityDto(
		@NotBlank(message = "El nombre es obligatorio") String name,
		@Min(value = 1, message = "El nivel debe ser mínimo 1") @Max(value = 5, message = "El nivel debe ser máximo 5") Integer level,
		@Min(value = 1, message = "El máximo de días de respuesta debe ser mínimo 1") @Max(value = 15, message = "El máximo de días de respuesta debe ser máximo 15") Integer max_response_days) {
}