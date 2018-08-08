package com.example.demo.service.impl;

import com.example.demo.entities.Product;
import com.example.demo.repository.ProductRepository;
import com.example.demo.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

/**
 * Created by ruzieljonm on 04/08/2018.
 */
@Service("productService")
public class ProductServiceImpl implements ProductService {

    @Autowired
    ProductRepository productRepository;

    public void save(Product newProd) {
        productRepository.save(newProd);
    }

    public ArrayList<Product> findAllByProductsellerid(int userid) {
        return productRepository.findAllByProductsellerid(userid);
    }

    public void deleteByProductid(int i) {
        productRepository.deleteByProductid(i);
    }

    public ArrayList<Product> findAll() {
        return productRepository.findAll();
    }

    public Product findByProductid(int productid) {
        return productRepository.findByProductid(productid);
    }


}
