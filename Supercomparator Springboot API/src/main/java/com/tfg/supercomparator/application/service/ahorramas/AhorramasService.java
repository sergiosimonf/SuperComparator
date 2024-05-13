package com.tfg.supercomparator.application.service.ahorramas;

import com.tfg.supercomparator.domain.ahorramas.AhorramasProductHistory;
import com.tfg.supercomparator.domain.ahorramas.AhorramasProductHistoryItem;
import org.apache.coyote.Response;
import org.springframework.http.ResponseEntity;

public interface AhorramasService {
    ResponseEntity<AhorramasProductHistoryItem> saveProductHistory(AhorramasProductHistoryItem ahorramasProductHistory);
}