package com.tfg.supercomparator.application.service.mercadona;


import com.tfg.supercomparator.domain.mercadona.MercadonaProduct;
import com.tfg.supercomparator.domain.mercadona.MercadonaProductHistoryItem;
import com.tfg.supercomparator.domain.mercadona.MercadonaProductHistoryItems;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface MercadonaService {
    List<MercadonaProduct> findProduct(String product);

    ResponseEntity<MercadonaProductHistoryItem> saveProductHistory(MercadonaProductHistoryItem mercadonaProductHistory);

    ResponseEntity<MercadonaProductHistoryItems> saveProductsHistory(MercadonaProductHistoryItems mercadonaProductHistory);
}