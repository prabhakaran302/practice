package com.practice.crud.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.practice.crud.model.Product;

public interface ProductRespository extends JpaRepository<Product, Long> {
}
