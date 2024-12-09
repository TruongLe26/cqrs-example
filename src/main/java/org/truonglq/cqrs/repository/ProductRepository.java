package org.truonglq.cqrs.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.truonglq.cqrs.entity.Product;

public interface ProductRepository extends JpaRepository<Product, Long> {
}
