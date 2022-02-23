package com.entrega.model.document;

import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Setter
@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Document("carrito")
public class Carrito {
    @Id
    private String idCarrito;
    private String mail;
    List<CartItem> items = new ArrayList<CartItem>();

    public void addItem(CartItem item){
        items.add(item);
    }
}





