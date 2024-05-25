package com.tfg.supercomparator.application.ports.in.ahorramas;

import com.tfg.supercomparator.domain.ahorramas.AhorramasProductHistoryItem;
import com.tfg.supercomparator.domain.ahorramas.AhorramasProductHistoryItems;
import org.springframework.http.ResponseEntity;

public interface AhorramasSaveProductHistoryUseCase {
    ResponseEntity<AhorramasProductHistoryItem> saveProductHistory(AhorramasProductHistoryItem ahorramasProductHistory);

    ResponseEntity<AhorramasProductHistoryItems> saveProductsHistory(AhorramasProductHistoryItems ahorramasProductHistory);
}