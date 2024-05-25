package com.tfg.supercomparator.infrastructure.controllers.eroski;

import com.tfg.supercomparator.application.ports.in.eroski.EroskiSaveProductHistoryUseCase;
import com.tfg.supercomparator.domain.eroski.EroskiProductHistoryItem;
import com.tfg.supercomparator.domain.eroski.EroskiProductHistoryItems;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequestMapping("api/eroski/")
@AllArgsConstructor
public class EroskiController {

    private EroskiSaveProductHistoryUseCase saveProductHistory;

    @PostMapping("item")
    public ResponseEntity<EroskiProductHistoryItem> saveProductHistoryDiaController(
            @RequestBody() EroskiProductHistoryItem product
    ) {
        return saveProductHistory.saveProductHistory(product);
    }

    @PostMapping("")
    public ResponseEntity<EroskiProductHistoryItems> saveProductsHistory(
            @RequestBody() EroskiProductHistoryItems products
    ) {
        return saveProductHistory.saveProductsHistory(products);
    }
}