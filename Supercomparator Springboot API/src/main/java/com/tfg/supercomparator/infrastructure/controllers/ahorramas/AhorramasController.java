package com.tfg.supercomparator.infrastructure.controllers.ahorramas;

import com.tfg.supercomparator.application.ports.in.ahorramas.AhorramasSaveProductHistoryUseCase;
import com.tfg.supercomparator.domain.ahorramas.AhorramasProductHistoryItem;
import com.tfg.supercomparator.domain.ahorramas.AhorramasProductHistoryItems;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequestMapping("api/ahorramas/")
@AllArgsConstructor
public class AhorramasController {

    private AhorramasSaveProductHistoryUseCase saveProductHistory;

    @PostMapping("item")
    public ResponseEntity<AhorramasProductHistoryItem> saveProductHistory(
            @RequestBody() AhorramasProductHistoryItem product
    ) {
        log.info("saveProductHistory {}", product);
        return saveProductHistory.saveProductHistory(product);
    }

    @PostMapping("")
    public ResponseEntity<AhorramasProductHistoryItems> saveProductsHistory(
            @RequestBody() AhorramasProductHistoryItems products
    ) {
        return saveProductHistory.saveProductsHistory(products);
    }
}