package com.entrega.service.impl;

import com.entrega.cache.CacheClient;
import com.entrega.handle.ApiRestException;
import com.entrega.model.document.Product;
import com.entrega.repository.ProductRepository;
import com.entrega.service.ProductoService;
import com.fasterxml.jackson.core.JsonProcessingException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Slf4j
@Service
@RequiredArgsConstructor
public class ProductoServiceImpl implements ProductoService {

    private final ProductRepository repository;
    private final CacheClient<Product> cache;

    @Override
    public Product create(Product product) {
        try {
            var data = repository.save(product);
            return saveProductInCache(data);
        } catch (JsonProcessingException e) {
            log.error("Error converting product to string", e);
        }
        return product;
    }

    @Override
    public List<Product> findAll() {
        return new ArrayList<>(repository.findAll());
    }

    @Override
    public Product getProductById(String id) {
        try {
            var dataFromCache = cache.recover(id, Product.class);
            if (!Objects.isNull(dataFromCache)) {
                return dataFromCache;
            }
            var dataFromDatabase = repository.findById(id)
                    .orElseThrow(ApiRestException::new);
            return saveProductInCache(dataFromDatabase);
        } catch (JsonProcessingException e) {
            log.error("Error converting product to string", e);
        } catch (ApiRestException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public Product updateProductById(Product producto, String id) {
        producto.setId(id);
        return repository.save(producto);
    }

    @Override
    public void delete(String id) {
        repository.deleteById(id);
    }

    @Override
    public List<Product> findByCategory(String category) {
        return new ArrayList<>(repository.findByCategory(category));
    }


    private Product saveProductInCache(Product producto) throws JsonProcessingException {
        return cache.save(producto.getId(), producto);
    }

}
