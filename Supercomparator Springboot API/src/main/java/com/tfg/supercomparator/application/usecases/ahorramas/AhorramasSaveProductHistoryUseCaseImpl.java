package com.tfg.supercomparator.application.usecases.ahorramas;

import com.tfg.supercomparator.application.ports.in.ahorramas.AhorramasSaveProductHistoryUseCase;
import com.tfg.supercomparator.application.service.ahorramas.AhorramasService;
import com.tfg.supercomparator.domain.ahorramas.AhorramasProductHistoryItem;
import com.tfg.supercomparator.domain.ahorramas.AhorramasProductHistoryItems;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

@Slf4j
@Component
@AllArgsConstructor
public class AhorramasSaveProductHistoryUseCaseImpl implements AhorramasSaveProductHistoryUseCase {

    private AhorramasService ahorramasService;

    @Override
    public ResponseEntity<AhorramasProductHistoryItem> saveProductHistory(AhorramasProductHistoryItem ahorramasProductHistory) {
        log.debug("Saving product history  {}", ahorramasProductHistory);
        return ahorramasService.saveProductHistory(ahorramasProductHistory);
    }

    @Override
    public ResponseEntity<AhorramasProductHistoryItems> saveProductsHistory(AhorramasProductHistoryItems ahorramasProductHistory) {
        log.debug("Saving products history  {}", ahorramasProductHistory);
        return ahorramasService.saveProductsHistory(ahorramasProductHistory);
    }
}