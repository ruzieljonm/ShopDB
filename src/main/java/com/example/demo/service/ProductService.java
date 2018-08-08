package com.example.demo.service;

import com.example.demo.entities.Product;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

/**
 * Created by ruzieljonm on 04/08/2018.
 */
@Service
public interface ProductService {
    void save(Product newProd);

    ArrayList<Product> findAllByProductsellerid(int userid);

    void deleteByProductid(int i);

    ArrayList<Product> findAll();

    Product findByProductid(int productid);
}
