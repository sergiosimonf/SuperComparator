package com.tfg.supercomparator.infrastructure.controllers.alcampo;

import com.tfg.supercomparator.application.ports.in.alcampo.AlacampoSaveProductHistoryUseCase;
import com.tfg.supercomparator.domain.alcampo.AlcampoProductHistory;
import com.tfg.supercomparator.domain.alcampo.AlcampoProductHistoryItem;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RestController
@RequestMapping("api/alcampo/")
@AllArgsConstructor
public class AlcampoController {

    private AlacampoSaveProductHistoryUseCase saveProductHistory;

    @PostMapping("")
    public ResponseEntity<AlcampoProductHistoryItem> saveProductHistory(
            @RequestBody() AlcampoProductHistoryItem product
    ) {
        return saveProductHistory.saveProductHistory(product);
    }
}