package com.cesde.retosbiz.Repository;

import java.util.List;

import com.cesde.retosbiz.Dto.CategoryDto;
import com.cesde.retosbiz.Dto.UpdateCategoryDto;

public interface CategoryRepository {
	List<CategoryDto> getAllCategories();
	CategoryDto getCategoryById(String id);
	CategoryDto createCategory(CategoryDto categoryDto);
	CategoryDto updateCategory(String id, UpdateCategoryDto updateCategoryDto);
	void deleteCategory(String id);
}