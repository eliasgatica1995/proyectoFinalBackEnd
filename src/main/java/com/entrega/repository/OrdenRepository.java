package com.entrega.repository;

import com.entrega.model.document.Orden;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;

public interface OrdenRepository extends MongoRepository<Orden, String> {
    @Query(value = "{}", count = true)
    public Long countAllDocuments();
}
