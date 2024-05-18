package com.tfg.supercomparator.infrastructure.controllers.dia;

import com.tfg.supercomparator.application.ports.in.dia.DiaSaveProductHistoryUseCase;
import com.tfg.supercomparator.domain.dia.DiaProductHistory;
import com.tfg.supercomparator.domain.dia.DiaProductHistoryItem;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RestController
@RequestMapping("api/dia/")
@AllArgsConstructor
public class DiaController {

    private DiaSaveProductHistoryUseCase saveProductHistory;

    @PostMapping("")
    public ResponseEntity<DiaProductHistoryItem> saveProductHistoryDiaController(
            @RequestBody() DiaProductHistoryItem product
    ) {
        return saveProductHistory.saveProductHistory(product);
    }
}