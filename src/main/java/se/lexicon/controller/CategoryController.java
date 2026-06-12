package se.lexicon.controller;


import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import se.lexicon.dto.CategoryResponseDto;
import se.lexicon.service.CategoryService;

import java.util.List;

/**
 * REST controller for managing categories.
 * Exposes endpoints under /api/v1/categories.
 */
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/categories")
public class CategoryController {

    private final CategoryService categoryService;

    /**
     * Creates a new category.
     *
     * @param name the name of the category to create
     * @return the created category with HTTP 201 Created
     */
    @PostMapping
    public ResponseEntity<CategoryResponseDto> create(@RequestParam String name) {
        CategoryResponseDto response = categoryService.create(name);
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    /**
     * Retrieves all categories.
     *
     * @return list of all categories with HTTP 200 OK
     */

    @GetMapping
    public ResponseEntity<List<CategoryResponseDto>> findAll() {
        List<CategoryResponseDto> categories = categoryService.findAll();
        return ResponseEntity.ok(categories);
    }

}
