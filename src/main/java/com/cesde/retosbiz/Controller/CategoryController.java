package com.cesde.retosbiz.Controller;

import java.util.List;

import org.springframework.web.bind.annotation.RestController;

import com.cesde.retosbiz.Service.CategoryService;

import jakarta.validation.Valid;

import com.cesde.retosbiz.Dto.CategoryDto;
import com.cesde.retosbiz.Dto.UpdateCategoryDto;
import com.cesde.retosbiz.Exception.CategoryAlreadyExistException;
import com.cesde.retosbiz.Exception.CategoryNotFoundException;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;

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

@RestController
@RequestMapping("/categories")
@CrossOrigin(origins = "*")
@Tag(name = "Categorías", description = "Operaciones relacionadas con la gestión de categorías")
public class CategoryController {
	private final CategoryService categoryService;

	public CategoryController(CategoryService categoryService) {
		this.categoryService = categoryService;
	}

	@GetMapping
	@Operation(summary = "Obtener todas las categorías", description = "Retorna una lista con todas las categorías registradas en el sistema.")
	public ResponseEntity<List<CategoryDto>> getAllCategories() {
		try {
			return ResponseEntity.ok(this.categoryService.getAllCategories());
		} catch (Exception e) {
			throw new RuntimeException("Error al consultar las categorías: ", e);
		}
	}

	@GetMapping("/{id}")
	@Operation(summary = "Obtener una categoría por ID", description = "Busca y retorna una categoría específica según el identificador proporcionado.")
	public ResponseEntity<CategoryDto> getCategoryById(@PathVariable String id) {
		try {
			CategoryDto categoryDto = this.categoryService.getCategoryById(id);

			if (categoryDto == null)
				return ResponseEntity.notFound().build();

			return ResponseEntity.ok(categoryDto);
		} catch (CategoryNotFoundException e) {
			throw e;
		} catch (Exception e) {
			throw new RuntimeException("Error al consultar la categoría " + id, e);
		}
	}

	@PostMapping
	@Operation(summary = "Crear una nueva categoría", description = "Registra una nueva categoría en el sistema utilizando los datos proporcionados.")
	public ResponseEntity<CategoryDto> createCategory(@RequestBody CategoryDto categoryDto) {
		try {
			CategoryDto createdCategory = this.categoryService.createCategory(categoryDto);

			return ResponseEntity.status(HttpStatus.CREATED).body(createdCategory);
		} catch (CategoryAlreadyExistException e) {
			throw e;
		} catch (Exception e) {
			throw new RuntimeException("Error al crear la categoría", e);
		}
	}

	@PutMapping("/{id}")
	@Operation(summary = "Actualizar una categoría", description = "Actualiza la información de una categoría existente basada en su ID.")
	public ResponseEntity<CategoryDto> updateCategory(@PathVariable String id, @RequestBody @Valid UpdateCategoryDto updateCategoryDto) {
		try {
			return ResponseEntity.ok(this.categoryService.updateCategory(id, updateCategoryDto));
		} catch (CategoryNotFoundException e) {
			throw e;
		} catch (Exception e) {
			throw new RuntimeException("Error al actualizar la categoría " + id, e);
		}
	}

	@DeleteMapping("/{id}")
	@Operation(summary = "Eliminar una categoría", description = "Elimina una categoría del sistema utilizando su identificador único.")
	public ResponseEntity<Void> deleteCategory(@PathVariable String id) {
		try {
			this.categoryService.deleteCategory(id);
			return ResponseEntity.noContent().build();
		} catch (CategoryNotFoundException e) {
			throw e;
		} catch (Exception e) {
			throw new RuntimeException("Error al eliminar la categoría " + id, e);
		}
	}
}