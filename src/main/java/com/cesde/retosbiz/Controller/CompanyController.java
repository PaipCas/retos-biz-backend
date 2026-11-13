package com.cesde.retosbiz.Controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.cesde.retosbiz.Dto.CompanyDto;
import com.cesde.retosbiz.Dto.UpdateCompanyDto;
import com.cesde.retosbiz.Exception.CompanyAlreadyExistException;
import com.cesde.retosbiz.Exception.CompanyNotFoundException;
import com.cesde.retosbiz.Service.CompanyService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/companies")
@CrossOrigin(origins = "*")
@Tag(name = "Empresas", description = "Operaciones relacionadas con la gestión de empresas")
public class CompanyController {
	private final CompanyService companyService;

	public CompanyController(CompanyService companyService) {
		this.companyService = companyService;
	}

	@GetMapping
	@Operation(summary = "Obtener todas las empresas", description = "Retorna una lista con todas las empresas registradas en el sistema.")
	public ResponseEntity<List<CompanyDto>> getAllCompanies() {
		try {
			return ResponseEntity.ok(this.companyService.getAllCompanies());
		} catch (Exception e) {
			throw new RuntimeException("Error al consultar las empresas: ", e);
		}
	}

	@GetMapping("/{id}")
	@Operation(summary = "Obtener una empresa por ID", description = "Busca y retorna una empresa específica según el identificador proporcionado.")
	public ResponseEntity<CompanyDto> getCompanyById(@PathVariable String id) {
		try {
			CompanyDto companyDto = this.companyService.getCompanyById(id);

			if (companyDto == null)
				return ResponseEntity.notFound().build();

			return ResponseEntity.ok(companyDto);
		} catch (CompanyNotFoundException e) {
			throw e;
		} catch (Exception e) {
			throw new RuntimeException("Error al consultar la empresa " + id, e);
		}
	}

	@PostMapping
	@Operation(summary = "Crear una nueva empresa", description = "Registra una nueva empresa en el sistema utilizando los datos proporcionados.")
	public ResponseEntity<CompanyDto> createCompany(@RequestBody CompanyDto companyDto) {
		try {
			CompanyDto createdCompany = this.companyService.createCompany(companyDto);

			return ResponseEntity.status(HttpStatus.CREATED).body(createdCompany);
		} catch (CompanyAlreadyExistException e) {
			throw e;
		} catch (Exception e) {
			throw new RuntimeException("Error al crear la empresa", e);
		}
	}

	@PutMapping("/{id}")
	@Operation(summary = "Actualizar una empresa", description = "Actualiza la información de una empresa existente basada en su ID.")
	public ResponseEntity<CompanyDto> updateCompany(@PathVariable String id,
			@RequestBody @Valid UpdateCompanyDto updateCompanyDto) {
		try {
			return ResponseEntity.ok(this.companyService.updateCompany(id, updateCompanyDto));
		} catch (CompanyNotFoundException e) {
			throw e;
		} catch (Exception e) {
			throw new RuntimeException("Error al actualizar la empresa " + id, e);
		}
	}

	@DeleteMapping("/{id}")
	@Operation(summary = "Eliminar una empresa", description = "Elimina una empresa del sistema utilizando su identificador único.")
	public ResponseEntity<Void> deleteCompany(@PathVariable String id) {
		try {
			this.companyService.deleteCompany(id);
			return ResponseEntity.noContent().build();
		} catch (CompanyNotFoundException e) {
			throw e;
		} catch (Exception e) {
			throw new RuntimeException("Error al eliminar la empresa " + id, e);
		}
	}
}