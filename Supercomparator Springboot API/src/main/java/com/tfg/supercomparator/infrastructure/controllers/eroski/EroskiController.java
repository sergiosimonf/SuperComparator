package com.tfg.supercomparator.infrastructure.controllers.eroski;

import com.tfg.supercomparator.application.ports.in.eroski.EroskiSaveProductHistoryUseCase;
import com.tfg.supercomparator.domain.eroski.EroskiProductHistory;
import com.tfg.supercomparator.domain.eroski.EroskiProductHistoryItem;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RestController
@RequestMapping("api/eroski/")
@AllArgsConstructor
public class EroskiController {

    private EroskiSaveProductHistoryUseCase saveProductHistory;

    @PostMapping("")
    public ResponseEntity<EroskiProductHistoryItem> saveProductHistoryDiaController(
            @RequestBody() EroskiProductHistoryItem product
    ) {
        return saveProductHistory.saveProductHistory(product);
    }
}