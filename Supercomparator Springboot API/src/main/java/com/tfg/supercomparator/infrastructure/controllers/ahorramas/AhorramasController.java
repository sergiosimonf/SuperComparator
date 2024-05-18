package com.tfg.supercomparator.infrastructure.controllers.ahorramas;

import com.tfg.supercomparator.application.ports.in.ahorramas.AhorramasSaveProductHistoryUseCase;
import com.tfg.supercomparator.domain.ahorramas.AhorramasProductHistory;
import com.tfg.supercomparator.domain.ahorramas.AhorramasProductHistoryItem;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RestController
@RequestMapping("api/ahorramas/")
@AllArgsConstructor
public class AhorramasController {

    private AhorramasSaveProductHistoryUseCase saveProductHistory;

    @PostMapping("")
    public ResponseEntity<AhorramasProductHistoryItem> saveProductHistory(
            @RequestBody() AhorramasProductHistoryItem product
    ) {
        log.info("saveProductHistory {}", product);
        return saveProductHistory.saveProductHistory(product);
    }
}