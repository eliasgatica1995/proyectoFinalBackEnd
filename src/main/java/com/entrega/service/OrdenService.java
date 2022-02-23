package com.entrega.service;

import com.entrega.model.document.Orden;

public interface OrdenService {
    void save(Orden ordenFinal);
    public long count();

}
