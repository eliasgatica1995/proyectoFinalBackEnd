package com.entrega.builder;

import com.entrega.model.document.Product;
import com.entrega.model.request.ProductRequest;
import com.entrega.model.response.ProductResponse;

public class ProductBuilder {
        public static Product requestToDocument(ProductRequest request) {
        return Product.builder()
                .id(request.getId())
                .name(request.getName())
                .stock(request.getStock())
                .precio(request.getPrecio())
                .category(request.getCategoria())
                .build();
    }

    public static ProductResponse documentToResponse(Product producto) {
        return ProductResponse.builder()
                .name(producto.getName())
                .build();
    }


}
