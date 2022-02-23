package com.entrega.service.impl;

import com.entrega.model.document.Orden;
import com.entrega.repository.OrdenRepository;
import com.entrega.service.OrdenService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
public class OrdenServiceImpl implements OrdenService {

    private final OrdenRepository repository;


    @Override
    public void save(Orden ordenFinal) {
        repository.save(ordenFinal);
    }

    @Override
    public long count() {
       return repository.count();
    }
}

