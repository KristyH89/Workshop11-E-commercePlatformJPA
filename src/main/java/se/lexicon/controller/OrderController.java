package se.lexicon.controller;


import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import se.lexicon.dto.OrderRequestDto;
import se.lexicon.dto.OrderResponseDto;
import se.lexicon.service.OrderService;

import java.util.List;

@RestController
@RequestMapping("/api/orders")
@RequiredArgsConstructor
public class OrderController {

    private final OrderService orderService;

    // Create a new order based on the incoming request. Returns the created order including generated ID, timestamp and items.
    @PostMapping
    public ResponseEntity<OrderResponseDto> placeOrder(@RequestBody OrderRequestDto request) {
        OrderResponseDto response = orderService.placeOrder(request);
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    // Fetch a single order by its ID. Throws ResourceNotFoundException if the order does not exist.
    @GetMapping("/{id}")
    public ResponseEntity<OrderResponseDto> findById(@PathVariable Long id) {
        OrderResponseDto response = orderService.findById(id);
        return ResponseEntity.ok(response);
    }

    // Fetch all orders in the system. Useful for admin views or debugging.
    @GetMapping
    public ResponseEntity<List<OrderResponseDto>> findAll() {
        List<OrderResponseDto> response = orderService.findAll();
        return ResponseEntity.ok(response);
    }
}
