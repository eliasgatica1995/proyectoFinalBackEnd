package com.entrega.service.impl;

import com.entrega.handle.ApiRestException;
import com.entrega.model.document.Carrito;
import com.entrega.model.document.CartItem;
import com.entrega.model.document.Product;
import com.entrega.repository.CarritoRepository;
import com.entrega.service.CarritoService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
public class CarritoServiceImpl implements CarritoService {

    private final CarritoRepository repository;



    @Override
    public String create(Carrito carrito) {
        repository.save(carrito);
        return "Carrito creado";
    }

    @Override
    public String add(String id, CartItem item) throws ApiRestException {
        var aux=  repository.findById(id).orElseThrow(ApiRestException::new);;
        aux.addItem(item);
        aux.setIdCarrito(id);
        repository.save(aux);
        return "Producto guardado";
    }

    @Override
    public List<CartItem> getCarritoById(String id) throws ApiRestException {
        var carrito = repository.findById(id)
                .orElseThrow(ApiRestException::new);
        List<CartItem> items = carrito.getItems();
        return items;
    }
    @Override
    public Carrito updateCarritoById(Carrito carrito, String id) {
        carrito.setIdCarrito(id);
        return repository.save(carrito);
    }
    @Override
    public void delete(String id) {
            repository.deleteById(id);
    }

    @Override
    public Carrito getCarritoCompleto(String id) throws ApiRestException {
        return  repository.findById(id)
                .orElseThrow(ApiRestException::new);
    }
}
