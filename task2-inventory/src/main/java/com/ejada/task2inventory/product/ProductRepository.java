package com.ejada.task2inventory.product;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductRepository extends JpaRepository<Product, Integer> {
	
	@Query("SELECT p FROM Product p WHERE p.isDeleted = false")
    List<Product> findAllNotDeleted();
	
	@Query("SELECT p FROM Product p WHERE p.id = :id AND p.isDeleted = false")
    Optional<Product> findByIdNotDeleted(@Param("id") Integer id);
	
	@Query("SELECT p FROM Product p WHERE p.id IN (:ids) AND p.isDeleted = false")
    List<Product> findAllByIdNotDeleted(@Param("ids") List<Integer> ids);

    @Modifying
    @Query("UPDATE Product p SET p.isDeleted = true WHERE p.id = :id")
    void softDelete(@Param("id") Integer id);
    
}
