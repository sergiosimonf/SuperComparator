package com.tfg.supercomparator.infrastructure.controllers.carrefour;

import com.tfg.supercomparator.application.ports.in.carrefour.CarrefourSaveProductHistoryUseCase;
import com.tfg.supercomparator.domain.carrefour.CarrefourProductHistory;
import com.tfg.supercomparator.domain.carrefour.CarrefourProductHistoryItem;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RestController
@RequestMapping("api/carrefour/")
@AllArgsConstructor
public class CarrefourController {

    private CarrefourSaveProductHistoryUseCase saveProductHistory;

    @PostMapping("")
    public ResponseEntity<CarrefourProductHistoryItem> saveProductHistory(
            @RequestBody() CarrefourProductHistoryItem product
    ) {
        return saveProductHistory.saveProductHistory(product);
    }
}