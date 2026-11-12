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

	public List<CategoryDto> getAllCategories() throws Exception {
		try {
			return this.categoryRepository.getAllCategories();
		} catch (Exception error) {
			throw new Exception(error.getMessage());
		}
	}

	public CategoryDto getCategoryById(String id) throws Exception {
		try {
			CategoryDto categoryDto = this.categoryRepository.getCategoryById(id);

			if (categoryDto == null) {
				throw new CategoryNotFoundException(id);
			}

			return categoryDto;
		} catch (Exception error) {
			throw new Exception(error.getMessage());
		}
	}

	public CategoryDto createCategory(CategoryDto categoryDto) throws Exception {
		try {
			if (this.categoryRepository.existsByName(categoryDto.name())) {
				throw new CategoryAlreadyExistException(categoryDto.name());
			}

			return this.categoryRepository.createCategory(categoryDto);
		} catch (Exception error) {
			throw new Exception(error.getMessage());
		}
	}

	public CategoryDto updateCategory(String id, UpdateCategoryDto updateCategoryDto) throws Exception {
		try {
			if (!this.categoryRepository.existsById(id)) {
				throw new CategoryNotFoundException(id);
			}

			return this.categoryRepository.updateCategory(id, updateCategoryDto);
		} catch (Exception error) {
			throw new Exception(error.getMessage());
		}
	}

	public void deleteCategory(String id) throws Exception {
		try {
			if (!this.categoryRepository.existsById(id)) {
				throw new CategoryNotFoundException(id);
			}

			this.categoryRepository.deleteCategory(id);
		} catch (Exception error) {
			throw new Exception(error.getMessage());
		}
	}
}
