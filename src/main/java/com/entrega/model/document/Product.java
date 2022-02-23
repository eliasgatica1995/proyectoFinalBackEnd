package com.entrega.model.document;

import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.io.Serializable;

@Setter
@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Document("productos")
public class Product implements Serializable {

    @Id
    private String id;
    private String name;
    private int stock;
    private int precio;
    private String category;
}
