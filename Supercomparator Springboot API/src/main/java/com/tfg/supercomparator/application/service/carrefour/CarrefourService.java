package com.tfg.supercomparator.application.service.carrefour;


import com.tfg.supercomparator.domain.carrefour.CarrefourProductHistory;
import com.tfg.supercomparator.domain.carrefour.CarrefourProductHistoryItem;
import org.springframework.http.ResponseEntity;

public interface CarrefourService {
    ResponseEntity<CarrefourProductHistoryItem> saveProductHistory(CarrefourProductHistoryItem carrefourProductHistory);
}