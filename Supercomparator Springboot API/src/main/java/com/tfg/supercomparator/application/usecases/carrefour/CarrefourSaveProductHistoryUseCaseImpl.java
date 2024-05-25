package com.tfg.supercomparator.application.usecases.carrefour;


import com.tfg.supercomparator.application.ports.in.carrefour.CarrefourSaveProductHistoryUseCase;
import com.tfg.supercomparator.application.service.carrefour.CarrefourService;
import com.tfg.supercomparator.domain.carrefour.CarrefourProductHistoryItem;
import com.tfg.supercomparator.domain.carrefour.CarrefourProductHistoryItems;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

@Component
@AllArgsConstructor
public class CarrefourSaveProductHistoryUseCaseImpl implements CarrefourSaveProductHistoryUseCase {

    private CarrefourService carrefourService;

    @Override
    public ResponseEntity<CarrefourProductHistoryItem> saveProductHistory(CarrefourProductHistoryItem carrefourProductHistory) {
        return carrefourService.saveProductHistory(carrefourProductHistory);
    }

    @Override
    public ResponseEntity<CarrefourProductHistoryItems> saveProductsHistory(CarrefourProductHistoryItems carrefourProductHistory) {
        return carrefourService.saveProductsHistory(carrefourProductHistory);
    }
}