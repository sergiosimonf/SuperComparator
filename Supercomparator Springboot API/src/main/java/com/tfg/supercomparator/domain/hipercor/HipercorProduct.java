package com.tfg.supercomparator.domain.hipercor;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class HipercorProduct {
    private String nombre;
    private double precio;
    private double precioPorUnidad;
    private String precioPorUnidadText;
    private Double precioSinOferta;
    private String imageUrl;
    private String productUrl;
    private boolean haveOferta;
}
