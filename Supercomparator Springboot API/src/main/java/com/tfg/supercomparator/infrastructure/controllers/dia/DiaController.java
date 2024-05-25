package com.tfg.supercomparator.infrastructure.controllers.dia;

import com.tfg.supercomparator.application.ports.in.dia.DiaSaveProductHistoryUseCase;
import com.tfg.supercomparator.domain.dia.DiaProductHistoryItem;
import com.tfg.supercomparator.domain.dia.DiaProductHistoryItems;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequestMapping("api/dia/")
@AllArgsConstructor
public class DiaController {

    private DiaSaveProductHistoryUseCase saveProductHistory;

    @PostMapping("item")
    public ResponseEntity<DiaProductHistoryItem> saveProductHistoryDiaController(
            @RequestBody() DiaProductHistoryItem product
    ) {
        return saveProductHistory.saveProductHistory(product);
    }

    @PostMapping("")
    public ResponseEntity<DiaProductHistoryItems> saveProductsHistory(
            @RequestBody() DiaProductHistoryItems products
    ) {
        return saveProductHistory.saveProductsHistory(products);
    }
}