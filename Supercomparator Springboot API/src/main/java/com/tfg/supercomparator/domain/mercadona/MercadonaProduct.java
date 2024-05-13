package com.tfg.supercomparator.domain.mercadona;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class MercadonaProduct {
    private String name;
    private double price;
    private double pricePerQuantity;
    private String pricePerQuantityText;
    private Double priceDiscount;
    private Double priceNoDiscount;
    private String imageUrl;
    private boolean isDiscount;
}
