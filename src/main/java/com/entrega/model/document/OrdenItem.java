package com.entrega.model.document;

import lombok.*;
import org.springframework.data.annotation.Id;

@Setter
@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class OrdenItem {
    //de cart Item
    @Id
    private String id;
    private int cant;
    //de product
    private String name;
    private int precio;

}
