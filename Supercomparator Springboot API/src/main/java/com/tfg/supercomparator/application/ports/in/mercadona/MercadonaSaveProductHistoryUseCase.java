package com.tfg.supercomparator.application.ports.in.mercadona;


import com.tfg.supercomparator.domain.mercadona.MercadonaProductHistoryItem;
import org.springframework.http.ResponseEntity;

public interface MercadonaSaveProductHistoryUseCase {
    ResponseEntity<MercadonaProductHistoryItem> saveProductHistory(MercadonaProductHistoryItem mercadonaProductHistory);
}
