package com.devsuperior.dsdelivery.services;

import java.time.Instant;
import java.util.List;
import java.util.stream.Collectors;

import com.devsuperior.dsdelivery.dto.OrderDTO;
import com.devsuperior.dsdelivery.dto.ProductDTO;
import com.devsuperior.dsdelivery.entities.Order;
import com.devsuperior.dsdelivery.entities.OrderStatus;
import com.devsuperior.dsdelivery.entities.Product;
import com.devsuperior.dsdelivery.repositories.OrderRepository;
import com.devsuperior.dsdelivery.repositories.ProductRepository;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class OrderService {

    private final OrderRepository repository;

    private final ProductRepository productRepository;

    @Transactional(readOnly = true)
    public List<OrderDTO> findAll() {
        final List<Order> list = repository.findOrdersWithProducts();
        return list.stream().map(x -> new OrderDTO(x)).collect(Collectors.toList());
    }

    public OrderDTO insert(OrderDTO dto) {
        final Order order = new Order(null, dto.getAddress(), dto.getLatitude(), dto.getLongitude(), Instant.now(),
                OrderStatus.PENDING);

        for (ProductDTO p : dto.getProducts()) {
            final Product product = productRepository.findById(p.getId()).orElse(null);
            order.getProducts();
            order.getProducts().add(product);
        }

        repository.save(order);

        return new OrderDTO(order);
    }

}
