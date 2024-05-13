package com.tfg.supercomparator.application.ports.in.ahorramas;

import com.tfg.supercomparator.domain.ahorramas.AhorramasProductHistory;
import com.tfg.supercomparator.domain.ahorramas.AhorramasProductHistoryItem;
import org.springframework.http.ResponseEntity;

public interface AhorramasSaveProductHistoryUseCase {
    ResponseEntity<AhorramasProductHistoryItem> saveProductHistory(AhorramasProductHistoryItem ahorramasProductHistory);
}