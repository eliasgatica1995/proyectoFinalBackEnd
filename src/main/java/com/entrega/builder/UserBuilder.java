package com.entrega.builder;

import com.entrega.model.document.User;
import com.entrega.model.request.UserRequest;
import com.entrega.model.response.UserResponse;

public class UserBuilder {

    public static User requestToDocument(UserRequest request) {
        return User.builder()
                .username(request.getUsername())
                .email(request.getEmail())
                .password(request.getPassword())
                .build();
    }

    public static UserResponse documentToResponse(User user) {
        return UserResponse.builder()
                .username(user.getUsername())
                .email(user.getEmail())
                .build();
    }
}
