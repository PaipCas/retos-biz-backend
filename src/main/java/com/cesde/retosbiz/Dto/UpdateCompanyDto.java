package com.cesde.retosbiz.Dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public record UpdateCompanyDto(
        @NotBlank(message = "El nombre es obligatorio") String name,
        @NotBlank(message = "La dirección es obligatoria") @Size(max = 200, message = "La dirección no puede exceder los 200 caracteres") String address,
        @NotBlank(message = "El teléfono es obligatorio") @Size(min = 7, max = 15, message = "El teléfono debe tener entre 7 y 15 caracteres") String phone,
        @NotBlank(message = "El sector es obligatorio") @Size(max = 100, message = "El sector no puede exceder los 100 caracteres") String sector) {
}