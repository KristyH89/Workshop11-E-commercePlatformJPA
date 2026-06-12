package se.lexicon.service;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import se.lexicon.exception.ResourceNotFoundException;
import se.lexicon.dto.OrderItemRequestDto;
import se.lexicon.dto.OrderRequestDto;
import se.lexicon.dto.OrderResponseDto;
import se.lexicon.entity.Customer;
import se.lexicon.entity.Order;
import se.lexicon.entity.Product;
import se.lexicon.mapper.OrderMapper;
import se.lexicon.repository.CustomerRepository;
import se.lexicon.repository.OrderRepository;
import se.lexicon.repository.ProductRepository;

import java.util.List;

@Service
@RequiredArgsConstructor
public class OrderServiceImpl implements OrderService {

    private final OrderRepository orderRepository;
    private final CustomerRepository customerRepository;
    private final ProductRepository productRepository;
    private final OrderMapper orderMapper;

    @Override
    @Transactional
    public OrderResponseDto placeOrder(OrderRequestDto request) {

        // 1. Fetch the customer placing the order
        Customer customer = customerRepository.findById(request.customerId())
                .orElseThrow(() -> new ResourceNotFoundException(
                        "Customer not found with id: " + request.customerId()
                ));

        // 2. Extract all product IDs from the request
        List<Long> productIds = request.items().stream()
                .map(OrderItemRequestDto::productId)
                .toList();

        // 3. Fetch all products referenced in the order
        List<Product> products = productRepository.findAllById(productIds);
        if (products.size() != productIds.size()) {
            throw new ResourceNotFoundException("One or more products were not found");
        }

        // 4. Convert the request into an Order entity using the mapper
        Order order = orderMapper.toEntity(request, customer, products);

        // 5. Persist the order (items are saved automatically via cascade)
        Order savedOrder = orderRepository.save(order);

        // 6. Convert the saved order into a response DTO
        return orderMapper.toResponse(savedOrder);
    }

    @Override
    public OrderResponseDto findById(Long id) {
        // Fetch order or throw if not found
        Order order = orderRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException(
                        "Order not found with id: " + id
                ));

        return orderMapper.toResponse(order);
    }

    @Override
    public List<OrderResponseDto> findAll(){

        // Fetch all orders and convert them to response DTOs
        return orderRepository.findAll().stream()
                .map(orderMapper::toResponse)
                .toList();
    }
}
