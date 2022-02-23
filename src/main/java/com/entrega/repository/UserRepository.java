package com.entrega.repository;

import com.entrega.model.document.User;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends MongoRepository<User, String> {
    User findUserByUsername(String username);
    User findUserByEmail(String username);
}
