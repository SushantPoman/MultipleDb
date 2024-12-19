package com.multipledb.postgres.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import com.multipledb.postgres.entities.Products;

public interface ProductsRepo extends JpaRepository<Products, Integer>{

}
