package se.lexicon.controller;


import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import se.lexicon.dto.OrderRequestDto;
import se.lexicon.dto.OrderResponseDto;
import se.lexicon.service.OrderService;

import java.util.List;

/**
 * REST controller for managing orders.
 * Exposes endpoints under /api/v1/orders.
 */

@RestController
@RequestMapping("/api/v1/orders")
@RequiredArgsConstructor
public class OrderController {

    private final OrderService orderService;

    /**
     * Places a new order.
     *
     * @param request the order data containing customer and product details
     * @return the created order with HTTP 201 Created
     * @throws se.lexicon.exception.ResourceNotFoundException if the customer or a product does not exist
     */
    @PostMapping
    public ResponseEntity<OrderResponseDto> placeOrder(
            @Valid @RequestBody OrderRequestDto request) {
        OrderResponseDto response = orderService.placeOrder(request);
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    /**
     * Retrieves a single order by its ID.
     *
     * @param id the ID of the order
     * @return the found order with HTTP 200 OK
     * @throws se.lexicon.exception.ResourceNotFoundException if no order exists with the given ID
     */
    @GetMapping("/{id}")
    public ResponseEntity<OrderResponseDto> findById(@PathVariable Long id) {
        OrderResponseDto response = orderService.findById(id);
        return ResponseEntity.ok(response);
    }

    /**
     * Retrieves all orders in the system.
     *
     * @return list of all orders with HTTP 200 OK
     */
    @GetMapping
    public ResponseEntity<List<OrderResponseDto>> findAll() {
        List<OrderResponseDto> response = orderService.findAll();
        return ResponseEntity.ok(response);
    }
}
