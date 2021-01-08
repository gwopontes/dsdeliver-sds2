package com.devsuperior.dsdelivery.services;

import java.util.List;
import java.util.stream.Collectors;

import com.devsuperior.dsdelivery.dto.ProductDTO;
import com.devsuperior.dsdelivery.entities.Product;
import com.devsuperior.dsdelivery.repositories.ProductRepository;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class ProductService {

    private final ProductRepository repository;

    @Transactional(readOnly = true)
    public List<ProductDTO> findAll() {
        final List<Product> list = repository.findAll();
        return list.stream().map(x -> new ProductDTO(x)).collect(Collectors.toList());
    }

}
