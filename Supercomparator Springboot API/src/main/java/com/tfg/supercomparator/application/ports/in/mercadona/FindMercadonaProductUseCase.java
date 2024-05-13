package com.tfg.supercomparator.application.ports.in.mercadona;


import com.tfg.supercomparator.domain.mercadona.MercadonaProduct;

import java.util.List;

public interface FindMercadonaProductUseCase {
    List<MercadonaProduct> findProduct(String product);
}
