package com.tfg.supercomparator.domain.hipercor;

import lombok.Builder;
import lombok.Data;

@Data
public class HipercorProduct {
    private String nombre;
    private Double precio;
    private Double precioPorUnidad;
    private String precioPorUnidadText;
    private Double precioSinOferta;
    private String imageUrl;
    private String productUrl;
    private boolean haveOferta;

    public HipercorProduct(String nombre, Double precio, Double precioPorUnidad, String precioPorUnidadText, Double precioSinOferta, String imageUrl, String productUrl) {
        this.nombre = nombre;
        this.precio = precio;
        this.precioPorUnidad = precioPorUnidad;
        this.precioPorUnidadText = precioPorUnidadText;
        this.precioSinOferta = precioSinOferta;
        this.imageUrl = imageUrl;
        this.productUrl = productUrl;
        this.haveOferta = precioSinOferta != null;
    }
}
