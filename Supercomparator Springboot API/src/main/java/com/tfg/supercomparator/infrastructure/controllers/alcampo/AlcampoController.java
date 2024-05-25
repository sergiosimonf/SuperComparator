package com.tfg.supercomparator.infrastructure.controllers.alcampo;

import com.tfg.supercomparator.application.ports.in.alcampo.AlacampoSaveProductHistoryUseCase;
import com.tfg.supercomparator.domain.alcampo.AlcampoProductHistoryItem;
import com.tfg.supercomparator.domain.alcampo.AlcampoProductHistoryItems;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequestMapping("api/alcampo/")
@AllArgsConstructor
public class AlcampoController {

    private AlacampoSaveProductHistoryUseCase saveProductHistory;

    @PostMapping("item")
    public ResponseEntity<AlcampoProductHistoryItem> saveProductHistory(
            @RequestBody() AlcampoProductHistoryItem product
    ) {
        return saveProductHistory.saveProductHistory(product);
    }

    @PostMapping("")
    public ResponseEntity<AlcampoProductHistoryItems> saveProductsHistory(
            @RequestBody() AlcampoProductHistoryItems products
    ) {
        return saveProductHistory.saveProductsHistory(products);
    }
}