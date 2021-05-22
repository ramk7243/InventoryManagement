package com.demo.inventory.management.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.demo.inventory.management.entity.Product;

@Repository
public interface ProductRepository extends JpaRepository<Product, Integer> {

	Product findByIdAndIsActiveAndIsDeleted(Integer id, Boolean isActive, Boolean isDeleted);

	List<Product> findAllByIsActiveAndIsDeleted(Boolean isActive, Boolean isDeleted);


	List<Product> findAllByProductCategoryCategoryName(String productCategory);
}
