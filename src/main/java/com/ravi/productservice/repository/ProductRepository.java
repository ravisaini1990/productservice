package com.ravi.productservice.repository;

import com.ravi.productservice.modal.Product;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductRepository extends JpaRepository<Product, Long> {
}
