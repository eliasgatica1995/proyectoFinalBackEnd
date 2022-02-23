package com.entrega.model.document;

import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import java.util.ArrayList;
import java.util.List;

import java.time.format.DateTimeFormatter;
import java.time.LocalDateTime;
@Setter
@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Document("ordenes")
public class Orden {
    @Id
    private long idOrden;
    private String mail;
    List<OrdenItem> itemsOrden = new ArrayList<>();
    private String estado;
    private  LocalDateTime time;

    public void addItem(OrdenItem item){
        itemsOrden.add(item);
    }
}

