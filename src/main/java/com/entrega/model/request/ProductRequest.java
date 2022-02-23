package com.entrega.model.request;

import lombok.*;
import org.springframework.data.annotation.Id;

@Setter
@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ProductRequest {

    private String id;
    private String name;
    private int stock;
    private int precio;
    private String categoria;

}
