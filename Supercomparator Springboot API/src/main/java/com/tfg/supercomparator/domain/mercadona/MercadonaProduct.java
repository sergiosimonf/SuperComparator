package com.tfg.supercomparator.domain.mercadona;

import lombok.Data;

@Data
public class MercadonaProduct {
    private String name;
    private Double price;
    private Double pricePerQuantity;
    private String pricePerQuantityText;
    private Double priceDiscount;
    private Double priceNoDiscount;
    private String imageUrl;
    private boolean isDiscount;

    public MercadonaProduct(String name, Double price, Double pricePerQuantity, String pricePerQuantityText, Double priceDiscount, Double priceNoDiscount, String imageUrl) {
        this.name = name;
        this.price = price;
        this.pricePerQuantity = pricePerQuantity;
        this.pricePerQuantityText = pricePerQuantityText;
        this.priceDiscount = priceDiscount;
        this.priceNoDiscount = priceNoDiscount;
        this.imageUrl = imageUrl;
        this.isDiscount = priceDiscount != null;
    }
}
