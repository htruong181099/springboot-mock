package com.hoangtm14.spring.repository;

import com.hoangtm14.spring.model.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface ProductRepository extends JpaRepository<Product, UUID> {
    @Modifying
    @Query("UPDATE Product set isDeleted = true where id = :productId")
    void softDeleteProduct(UUID productId);

    List<Product> findByCodeIn(List<String> productIdsList);
}
