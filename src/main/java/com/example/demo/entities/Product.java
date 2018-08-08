package com.example.demo.entities;

import javax.persistence.*;
import java.io.Serializable;

/**
 * Created by ruzieljonm on 04/08/2018.
 */
@Entity
@Table(name="product")
public class Product implements Serializable{

    @Id
    @GeneratedValue
    @Column(name="productid")
    private int productid;


    @Column(name="productname")
    private String productname;

    @Column(name="productprice")
    private String productprice;


    @Column(name="productsellerid")
    private int productsellerid;


    public int getProductid() {
        return productid;
    }

    public void setProductid(int productid) {
        this.productid = productid;
    }

    public String getProductname() {
        return productname;
    }

    public void setProductname(String productname) {
        this.productname = productname;
    }

    public String getProductprice() {
        return productprice;
    }

    public void setProductprice(String productprice) {
        this.productprice = productprice;
    }

    public int getProductsellerid() {
        return productsellerid;
    }

    public void setProductsellerid(int productsellerid) {
        this.productsellerid = productsellerid;
    }
}
