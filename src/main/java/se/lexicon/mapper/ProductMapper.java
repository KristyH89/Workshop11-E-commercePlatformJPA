package se.lexicon.mapper;

import org.springframework.stereotype.Component;
import se.lexicon.dto.ProductRequestDto;
import se.lexicon.dto.ProductResponseDto;
import se.lexicon.entity.Category;
import se.lexicon.entity.Product;

@Component
public class ProductMapper {

    // ENTITY -> RESPONSE
    public ProductResponseDto toResponse(Product product) {
        if (product == null) return null;

        return new ProductResponseDto(
                product.getId(),
                product.getName(),
                product.getPrice(),
                // Flattened category name for readability
                product.getCategory() != null ? product.getCategory().getName() : null
        );
    }

    // REQUEST -> ENTITY
    public Product toEntity(ProductRequestDto request, Category category) {
        if (request == null) return null;

        // Build Product using setters
        Product product = new Product();
        product.setName(request.name());
        product.setPrice(request.price());
        product.setCategory(category); // Category must be fetched in the service layer

        return product;
    }
}
