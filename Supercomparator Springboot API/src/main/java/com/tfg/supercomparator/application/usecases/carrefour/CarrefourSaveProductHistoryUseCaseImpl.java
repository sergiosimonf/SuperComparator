package com.tfg.supercomparator.application.usecases.carrefour;


import com.tfg.supercomparator.application.ports.in.carrefour.CarrefourSaveProductHistoryUseCase;
import com.tfg.supercomparator.application.service.carrefour.CarrefourService;
import com.tfg.supercomparator.domain.carrefour.CarrefourProductHistory;
import com.tfg.supercomparator.domain.carrefour.CarrefourProductHistoryItem;
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
}