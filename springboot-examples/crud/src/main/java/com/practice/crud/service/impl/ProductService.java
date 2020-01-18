package com.practice.crud.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import com.practice.crud.model.Product;
import com.practice.crud.repository.ProductRespository;

import java.util.List;
import java.util.Optional;

@Service

@RequiredArgsConstructor
public class ProductService {
    private final ProductRespository productRespository;

    public List<Product> findAll() {
        return productRespository.findAll();
    }

    public Optional<Product> findById(Long id) {
        return productRespository.findById(id);
    }

    public Product save(Product stock) {
        return productRespository.save(stock);
    }

    public void deleteById(Long id) {
        productRespository.deleteById(id);
    }
}
