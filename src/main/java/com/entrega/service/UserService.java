package com.entrega.service;

import com.entrega.model.request.UserRequest;
import com.entrega.model.response.UserResponse;

public interface UserService {
    UserResponse getUser(UserRequest request) throws Exception;
    UserResponse register(UserRequest request) throws Exception;
}
