package com.cesde.retosbiz.Controller;

import java.util.List;

import org.springframework.web.bind.annotation.RestController;

import com.cesde.retosbiz.Service.CategoryService;
import com.cesde.retosbiz.Dto.CategoryDto;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;


@RestController
@RequestMapping("/categories")
public class CategoryController {
	private final CategoryService categoryService;

	public CategoryController(CategoryService categoryService) {
		this.categoryService = categoryService;
	}

	@GetMapping
	public ResponseEntity<List<CategoryDto>> getAllCategories() {
		return ResponseEntity.ok(this.categoryService.getAllCategories());
	}

	@GetMapping("/{id}")
	public ResponseEntity<CategoryDto> getCategoryById(@PathVariable String id) {
		CategoryDto categoryDto = this.categoryService.getCategoryById(id);

		if (categoryDto == null) {
			return ResponseEntity.notFound().build();
		}
		return ResponseEntity.ok(categoryDto);
	}

	@PostMapping
	public ResponseEntity<CategoryDto> createCategory(@RequestBody CategoryDto categoryDto) {
		CategoryDto createdCategory = this.categoryService.createCategory(categoryDto);
		return ResponseEntity.status(HttpStatus.CREATED).body(createdCategory);
	}
}