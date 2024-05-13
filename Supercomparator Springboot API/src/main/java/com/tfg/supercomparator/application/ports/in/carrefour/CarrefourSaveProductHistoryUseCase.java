package com.tfg.supercomparator.application.ports.in.carrefour;

import com.tfg.supercomparator.domain.carrefour.CarrefourProductHistory;
import com.tfg.supercomparator.domain.carrefour.CarrefourProductHistoryItem;
import org.springframework.http.ResponseEntity;

public interface CarrefourSaveProductHistoryUseCase {
    ResponseEntity<CarrefourProductHistoryItem> saveProductHistory(CarrefourProductHistoryItem carrefourProductHistory);
}
