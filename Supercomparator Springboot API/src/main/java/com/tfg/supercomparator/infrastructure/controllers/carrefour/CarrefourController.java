package com.tfg.supercomparator.infrastructure.controllers.carrefour;

import com.tfg.supercomparator.application.ports.in.carrefour.CarrefourSaveProductHistoryUseCase;
import com.tfg.supercomparator.domain.carrefour.CarrefourProductHistoryItem;
import com.tfg.supercomparator.domain.carrefour.CarrefourProductHistoryItems;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequestMapping("api/carrefour/")
@AllArgsConstructor
public class CarrefourController {

    private CarrefourSaveProductHistoryUseCase saveProductHistory;

    @PostMapping("item")
    public ResponseEntity<CarrefourProductHistoryItem> saveProductHistory(
            @RequestBody() CarrefourProductHistoryItem product
    ) {
        return saveProductHistory.saveProductHistory(product);
    }

    @PostMapping("")
    public ResponseEntity<CarrefourProductHistoryItems> saveProductsHistory(
            @RequestBody() CarrefourProductHistoryItems products
    ) {
        return saveProductHistory.saveProductsHistory(products);
    }
}