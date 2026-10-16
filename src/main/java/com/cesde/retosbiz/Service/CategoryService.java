package com.cesde.retosbiz.Service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.cesde.retosbiz.Dto.CategoryDto;
import com.cesde.retosbiz.Dto.UpdateCategoryDto;
import com.cesde.retosbiz.Repository.CategoryRepository;

@Service
public class CategoryService {
	private final CategoryRepository categoryRepository;

	public CategoryService(CategoryRepository categoryRepository) {
		this.categoryRepository = categoryRepository;
	}

	public List<CategoryDto> getAllCategories() {
		return this.categoryRepository.getAllCategories();
	}

	public CategoryDto getCategoryById(String id) {
		return this.categoryRepository.getCategoryById(id);
	}

	public CategoryDto createCategory(CategoryDto categoryDto) {
		return this.categoryRepository.createCategory(categoryDto);
	}

	public CategoryDto updateCategory(String id, UpdateCategoryDto updateCategoryDto) {
		return this.categoryRepository.updateCategory(id, updateCategoryDto);
	}

	public void deleteCategory(String id) {
		this.categoryRepository.deleteCategory(id);
	}
}
