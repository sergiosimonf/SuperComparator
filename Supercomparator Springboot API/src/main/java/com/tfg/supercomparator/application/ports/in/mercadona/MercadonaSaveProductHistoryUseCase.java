package com.tfg.supercomparator.application.ports.in.mercadona;


import com.tfg.supercomparator.domain.mercadona.MercadonaProductHistoryItem;
import com.tfg.supercomparator.domain.mercadona.MercadonaProductHistoryItems;
import org.springframework.http.ResponseEntity;

public interface MercadonaSaveProductHistoryUseCase {
    ResponseEntity<MercadonaProductHistoryItem> saveProductHistory(MercadonaProductHistoryItem mercadonaProductHistory);

    ResponseEntity<MercadonaProductHistoryItems> saveProductsHistory(MercadonaProductHistoryItems mercadonaProductHistory);
}
