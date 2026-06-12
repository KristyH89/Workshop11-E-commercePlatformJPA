package se.lexicon.service;

import se.lexicon.dto.CategoryResponseDto;

import java.util.List;

public interface CategoryService {

    // Create a new category
    CategoryResponseDto create(String name);

    // Return all categories
    List<CategoryResponseDto> findAll();
}
