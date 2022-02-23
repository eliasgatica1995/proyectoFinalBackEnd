package com.entrega.service;

import com.entrega.handle.ApiRestException;
import com.entrega.model.document.Carrito;
import com.entrega.model.document.CartItem;

import java.util.List;

public interface CarritoService {

    String create(Carrito carrito);
    String add(String id, CartItem item) throws ApiRestException;
    List<CartItem> getCarritoById(String id) throws ApiRestException;
    Carrito updateCarritoById(Carrito carrito, String id);
    void delete(String id);
    Carrito getCarritoCompleto(String id) throws ApiRestException;

}
