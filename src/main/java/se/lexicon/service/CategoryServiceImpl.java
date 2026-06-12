package se.lexicon.service;


import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import se.lexicon.dto.CategoryResponseDto;
import se.lexicon.entity.Category;
import se.lexicon.exception.CategoryAlreadyExistsException;
import se.lexicon.repository.CategoryRepository;

import java.security.PublicKey;
import java.util.List;

@Service
@RequiredArgsConstructor
public class CategoryServiceImpl implements CategoryService {
    private final CategoryRepository categoryRepository;

    @Override
    public CategoryResponseDto create(String name) {

        // Check if category name already exist
        if (categoryRepository.existsByNameIgnoreCase(name)) {
            throw new CategoryAlreadyExistsException(
                    "Category already exists with name: " + name
            );
        }

        // Create and save new category
        Category category = new Category();
        category.setName(name);
        Category saved = categoryRepository.save(category);
        return toResponse(saved);
    }

    @Override
    public List<CategoryResponseDto> findAll() {
        return categoryRepository.findAll()
                .stream()
                .map(this::toResponse)
                .toList();

    }

    private CategoryResponseDto toResponse(Category category) {
        return new CategoryResponseDto(
                category.getId(),
                category.getName()
        );
    }
}
