package se.lexicon.service;

import se.lexicon.dto.OrderRequestDto;
import se.lexicon.dto.OrderResponseDto;

import java.util.List;

public interface OrderService {

    OrderResponseDto placeOrder(OrderRequestDto request);

    OrderResponseDto findById(Long id);
    List<OrderResponseDto> findAll();
}
