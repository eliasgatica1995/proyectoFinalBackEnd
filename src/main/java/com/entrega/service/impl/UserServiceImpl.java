package com.entrega.service.impl;

import com.entrega.builder.UserBuilder;
import com.entrega.handle.ApiRestException;
import com.entrega.model.document.User;
import com.entrega.model.request.UserRequest;
import com.entrega.model.response.UserResponse;
import com.entrega.repository.UserRepository;
import com.entrega.security.JwtProvider;
import com.entrega.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    @Autowired
    EmailSenderImpl enviar;

    private final UserRepository repository;
    private final JwtProvider jwtProvider;
    private final PasswordEncoder passwordEncoder;

    @Override
    public UserResponse getUser(UserRequest request) throws ApiRestException {

        var user = getByUsername(request.getUsername());

        if (user == null || !passwordEncoder.matches(request.getPassword(), user.getPassword())) {
            throw ApiRestException.builder().message("El usuario o el password es inválido").build();
        }
        var token = jwtProvider.getJWTToken(request.getUsername());
        return UserResponse.builder().username(request.getUsername()).token(token).build();
    }

    @Override
    public UserResponse register(UserRequest request) throws ApiRestException {
        validateUser(request);
        request.setPassword(passwordEncoder.encode(request.getPassword()));
        UserBuilder.requestToDocument(request);
        var user = repository.save(UserBuilder.requestToDocument(request));
        enviar.sendEmail("Se ha creado el usuario con nombre: "+request.getUsername()+
                        " con mail "+request.getEmail()
                ,"Creacion de usuario "+request.getEmail());

        return UserBuilder.documentToResponse(user);

    }

    void validateUser(UserRequest request) throws ApiRestException {
        var user = getByUsername(request.getUsername());
        if (user != null) {
            throw ApiRestException.builder().message("El usuario ya existe").build();
        }
        user = repository.findUserByEmail(request.getEmail());
        if (user != null) {
            throw ApiRestException.builder().message("El correo ya se encuentra registrado").build();
        }
    }

    private User getByUsername(String username) {
        return repository.findUserByUsername(username);
    }
}
