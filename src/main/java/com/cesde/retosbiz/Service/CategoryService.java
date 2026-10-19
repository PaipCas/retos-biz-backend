package com.cesde.retosbiz.Service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.cesde.retosbiz.Dto.CategoryDto;
import com.cesde.retosbiz.Dto.UpdateCategoryDto;
import com.cesde.retosbiz.Exception.CategoryAlreadyExistException;
import com.cesde.retosbiz.Exception.CategoryNotFoundException;
import com.cesde.retosbiz.Repository.CategoryRepository;

@Service
public class CategoryService {
	private final CategoryRepository categoryRepository;

	public CategoryService(CategoryRepository categoryRepository) {
		this.categoryRepository = categoryRepository;
	}

	public List<CategoryDto> getAllCategories() {
		try {
			return this.categoryRepository.getAllCategories();
		} catch (Exception e) {
			throw new RuntimeException("Error al consultar las categorías: ", e);
		}
	}

	public CategoryDto getCategoryById(String id) {
		try {
			CategoryDto categoryDto = this.categoryRepository.getCategoryById(id);

			if (categoryDto == null) {
				throw new CategoryNotFoundException(id);
			}
			
			return categoryDto;
		} catch (CategoryNotFoundException e) {
			throw e;
		} catch (Exception e) {
			throw new RuntimeException("Error al consultar la categoría " + id, e);
		}
	}

	public CategoryDto createCategory(CategoryDto categoryDto) {
		try {
			if (this.categoryRepository.existsByName(categoryDto.name())) {
				throw new CategoryAlreadyExistException(categoryDto.name());
			}
			
			return this.categoryRepository.createCategory(categoryDto);
		} catch (CategoryAlreadyExistException e) {
			throw e;
		} catch (Exception e) {
			throw new RuntimeException("Error al crear la categoría", e);
		}
	}

	public CategoryDto updateCategory(String id, UpdateCategoryDto updateCategoryDto) {
		try {
			if (!this.categoryRepository.existsById(id)) {
				throw new CategoryNotFoundException(id);
			}

			return this.categoryRepository.updateCategory(id, updateCategoryDto);
		} catch (CategoryNotFoundException e) {
			throw e;
		} catch (Exception e) {
			throw new RuntimeException("Error al actualizar la categoría " + id, e);
		}
	}

	public void deleteCategory(String id) {
		try {
			if (!this.categoryRepository.existsById(id)) {
				throw new CategoryNotFoundException(id);
			}
			
			this.categoryRepository.deleteCategory(id);
		} catch (CategoryNotFoundException e) {
			throw e;
		} catch (Exception e) {
			throw new RuntimeException("Error al eliminar la categoría " + id, e);
		}
	}
}
