package com.entrega.repository;

import com.entrega.model.document.Carrito;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CarritoRepository extends MongoRepository<Carrito, String> {

}
