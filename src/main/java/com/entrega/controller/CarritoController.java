package com.entrega.controller;

import java.time.LocalDateTime;
import com.entrega.handle.ApiRestException;
import com.entrega.model.document.Carrito;
import com.entrega.model.document.CartItem;
import com.entrega.model.document.Orden;
import com.entrega.model.document.OrdenItem;
import com.entrega.service.CarritoService;
import com.entrega.service.OrdenService;
import com.entrega.service.ProductoService;
import com.entrega.service.impl.EmailSenderImpl;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RestController
@RequestMapping("/final")
@RequiredArgsConstructor
public class CarritoController {

    private final CarritoService service;

    private final ProductoService serviceProd;

    private final OrdenService serviceOrden;

    @Autowired
    EmailSenderImpl enviar;

    @PostMapping("/carrito/create")
    public String createCarrito(@RequestBody Carrito carrito) {
        log.info("POST crear carrito");
        return service.create(carrito);
    }
    @PostMapping("/carrito/add/{id}")
    public String add(@PathVariable String id, @RequestBody CartItem item) throws ApiRestException {
        log.info("POST add item a carrito con id "+id);
        return service.add(id,item);
    }
    @GetMapping("/carrito/{id}")
    public List<CartItem> getCarritoById(@PathVariable String id) throws ApiRestException {
        log.info("GET get carrito por Id igual a "+id);
        return service.getCarritoById(id);
    }
    @DeleteMapping("/carrito/{id}")
    public String deleteCarritoById(@PathVariable String id){
        log.info("DELETE delete de carrito con id "+id);
        service.delete(id);
        return  "Borrado carrito con "+id;
    }
    @PutMapping("/carrito/{id}")
    public String updateCarritoById(@PathVariable String id, @RequestBody  Carrito carrito) {
        log.info("UPDATE de contenido de carrito con id "+id);
         service.updateCarritoById(carrito, id);
        return "Se actualizo el contenido del carrito con id "+id;
    }

    @PostMapping("/carrito/ordenar/{id}")
    public String createOrden(@PathVariable String id) throws ApiRestException {
        String bodyMail = "";
        String itemsMail="";

        //get de carrito, pido sus items
        List<CartItem> contenido= service.getCarritoById(id);
        Orden ordenFinal = new Orden();

        ordenFinal.setIdOrden(serviceOrden.count()+1);
        ordenFinal.setMail(service.getCarritoCompleto(id).getMail());

        //busco cada item en repo de productos y lo voy agregando a la orden
        for (CartItem c: contenido) {

            //creo la orden con los datos
            OrdenItem item= new OrdenItem();

            //de cart Item
            item.setId(c.getId());
            item.setCant(c.getCant());
            //de product
            item.setName(serviceProd.getProductById(id).getName());
            item.setPrecio(serviceProd.getProductById(id).getPrecio()*c.getCant());

            itemsMail += " Producto: "+serviceProd.getProductById(id).getName()+
                    " Precio: "+serviceProd.getProductById(id).getPrecio()*c.getCant()+
                    " Cantidad: "+c.getCant()+"\n";
            ordenFinal.addItem(item);

        }

        LocalDateTime now = LocalDateTime.now();

        ordenFinal.setTime(now);
        //guardo orden en base de datos
        serviceOrden.save(ordenFinal);

        bodyMail += "Id orden: "+ordenFinal.getIdOrden()+"\n"+"Mail de usuario: "+ordenFinal.getMail()+"\n"
                +"Contenido de la orden: "+"\n"+itemsMail;
        //envio mail
        enviar.sendEmail(bodyMail,"Orden de Compra "+ordenFinal.getMail());

        log.info("POST orden creada");
        //borrar carrito
        service.delete(id);
        return "Se ha generado la orden con el numero: "+ordenFinal.getIdOrden();
    }

}
