package com.cesde.retosbiz.Repository;

import java.util.List;

import com.cesde.retosbiz.Dto.CategoryDto;

public interface CategoryRepository {
	List<CategoryDto> getAllCategories();
	CategoryDto getCategoryById(String id);
	CategoryDto createCategory(CategoryDto categoryDto);
}