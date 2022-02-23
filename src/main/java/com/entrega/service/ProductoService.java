package com.entrega.service;

import com.entrega.handle.ApiRestException;
import com.entrega.model.document.Product;

import java.util.List;
import java.util.Optional;

public interface ProductoService {

    Product create(Product product);
    List<Product> findAll();
    Product getProductById(String id);
    Product updateProductById(Product producto, String id);
    void delete(String id);
    List<Product> findByCategory(String category);

}
