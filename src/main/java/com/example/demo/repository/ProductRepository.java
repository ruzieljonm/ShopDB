package com.example.demo.repository;

import com.example.demo.entities.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;

/**
 * Created by ruzieljonm on 04/08/2018.
 */
@Repository("productRepository")
@Transactional
public interface ProductRepository extends JpaRepository<Product, Long> {
    ArrayList<Product> findAllByProductsellerid(int userid);

    void deleteByProductid(int i);

    ArrayList<Product> findAll();

    Product findByProductid(int productid);
}
