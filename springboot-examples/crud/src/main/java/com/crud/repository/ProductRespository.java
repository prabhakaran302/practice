package com.crud.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.crud.model.Product;

public interface ProductRespository extends JpaRepository<Product, Long> {
}
