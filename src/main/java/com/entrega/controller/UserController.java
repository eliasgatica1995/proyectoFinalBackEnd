package com.entrega.controller;

import com.entrega.model.request.UserRequest;
import com.entrega.model.response.UserResponse;
import com.entrega.service.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RestController
@RequestMapping("/final")
@RequiredArgsConstructor
public class UserController {

    private final UserService service;

    @PostMapping("user/login")
    public UserResponse login(@RequestBody UserRequest request) throws Exception {
        log.info("POST login de usuario");
        return service.getUser(request);
    }

    @PostMapping("user/register")
    public UserResponse register(@RequestBody UserRequest request) throws Exception {
        log.info("POST registro de usuario");
        return service.register(request);
    }

}
