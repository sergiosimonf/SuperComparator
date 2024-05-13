package com.tfg.supercomparator.application.service.mercadona;


import com.tfg.supercomparator.domain.mercadona.MercadonaProduct;
import com.tfg.supercomparator.domain.mercadona.MercadonaProductHistoryItem;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface MercadonaService {
    ResponseEntity<MercadonaProductHistoryItem> saveProductHistory(MercadonaProductHistoryItem mercadonaProductHistory);

    List<MercadonaProduct> findProduct(String product);
}