package com.entrega.controller;

import com.entrega.model.document.Product;
import com.entrega.service.ProductoService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RestController
@RequestMapping("/final")
@RequiredArgsConstructor
public class ProductController {

    private final ProductoService service;

    @GetMapping("/productos/{id}")
    public Product getProductById(@PathVariable String id) {
        log.info("GET obtener producto por el id");
        return service.getProductById(id);
    }

    @PostMapping("/productos/create")
    public Product createProduct(@RequestBody Product producto) {
        log.info("POST crear producto");
        return service.create(producto);
    }

    @GetMapping("/productos")
    public List<Product> findProducts() {
        log.info("GET de todos los productos");
        return service.findAll();
    }

    @PutMapping("/productos/{id}")
    public Product updateProductById(@PathVariable String id, @RequestBody  Product producto) {
        log.info("PUT update de producto con id "+id);
        return service.updateProductById(producto, id);
    }
    @GetMapping("/productos/filtro/{category}")
    public List<Product> findByCategory(@PathVariable String category) {
        log.info("GET busqueda de productos por la categoria "+category);
        return service.findByCategory(category);
    }
    @DeleteMapping("/productos/{id}")
    public String deleteProducto(@PathVariable String id) {
        log.info("DELETE borrado de producto con id "+id);
        service.delete(id);
        return "Borrado de producto "+id;
    }
}
