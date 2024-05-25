package com.tfg.supercomparator.application.service.carrefour;


import com.tfg.supercomparator.domain.carrefour.CarrefourProductHistoryItem;
import com.tfg.supercomparator.domain.carrefour.CarrefourProductHistoryItems;
import org.springframework.http.ResponseEntity;

public interface CarrefourService {
    ResponseEntity<CarrefourProductHistoryItem> saveProductHistory(CarrefourProductHistoryItem carrefourProductHistory);

    ResponseEntity<CarrefourProductHistoryItems> saveProductsHistory(CarrefourProductHistoryItems carrefourProductHistory);
}