package se.lexicon.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import se.lexicon.dto.ProductRequestDto;
import se.lexicon.dto.ProductResponseDto;
import se.lexicon.entity.Category;
import se.lexicon.entity.Product;
import se.lexicon.exception.ResourceNotFoundException;
import se.lexicon.mapper.ProductMapper;
import se.lexicon.repository.CategoryRepository;
import se.lexicon.repository.ProductRepository;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ProductServiceImpl implements ProductService {

    private final ProductRepository productRepository;
    private final CategoryRepository categoryRepository;
    private final ProductMapper productMapper;

    @Override
    public ProductResponseDto create(ProductRequestDto request) {

        // Fetch category or throw if not found
        Category category = categoryRepository.findById(request.categoryId())
                .orElseThrow(() -> new ResourceNotFoundException(
                        "Category not found with id: " + request.categoryId()
                ));

        // Convert request DTO to entity
        Product product = productMapper.toEntity(request, category);

        // Save product to database
        Product saved = productRepository.save(product);

        // Convert saved entity to response DTO
        return productMapper.toResponse(saved);
    }

    @Override
    public ProductResponseDto findById(Long id) {

        // Fetch product or throw if not found
        Product product = productRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException(
                        "Product not found with id: " + id
                ));

        return productMapper.toResponse(product);
    }

    @Override
    public List<ProductResponseDto> findAll() {

        // Fetch all products and map to DTO list
        return productRepository.findAll()
                .stream()
                .map(productMapper::toResponse)
                .toList();
    }

    @Override
    public List<ProductResponseDto> searchByName(String name) {

        // Search products by name and map to DTO list

        return productRepository.findByNameContainingIgnoreCase(name)
                .stream()
                .map(productMapper::toResponse)
                .toList();
    }

    @Override
    public ProductResponseDto update(Long id, ProductRequestDto request) {

        // Fetch existing product or throw if not found
        Product product = productRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException(
                        "Product not found with id: " + id
                ));

        // Fetch category or throw if not found
        Category category = categoryRepository.findById(request.categoryId())
                .orElseThrow(() -> new ResourceNotFoundException(
                        "Category not found with id: " + request.categoryId()
                ));

        // Update fields
        product.setName(request.name());
        product.setPrice(request.price());
        product.setCategory(category);

        // Save and return updated product
        Product updated = productRepository.save(product);
        return productMapper.toResponse(updated);
    }

    @Override
    public void delete(Long id) {

        // Throw if product does not exist
        if (!productRepository.existsById(id)) {
            throw new ResourceNotFoundException("Product not found with id: " + id);
        }
        productRepository.deleteById(id);
    }

}