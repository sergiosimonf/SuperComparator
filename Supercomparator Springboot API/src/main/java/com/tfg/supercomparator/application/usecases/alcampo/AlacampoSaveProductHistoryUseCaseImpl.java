package com.tfg.supercomparator.application.usecases.alcampo;

import com.tfg.supercomparator.application.ports.in.alcampo.AlacampoSaveProductHistoryUseCase;
import com.tfg.supercomparator.application.service.alcampo.AlcampoService;
import com.tfg.supercomparator.domain.alcampo.AlcampoProductHistory;
import com.tfg.supercomparator.domain.alcampo.AlcampoProductHistoryItem;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

@Component
@AllArgsConstructor
public class AlacampoSaveProductHistoryUseCaseImpl implements AlacampoSaveProductHistoryUseCase {

    private AlcampoService alcampoService;

    @Override
    public ResponseEntity<AlcampoProductHistoryItem> saveProductHistory(AlcampoProductHistoryItem alcampoProductHistory) {
        return alcampoService.saveProductHistory(alcampoProductHistory);
    }
}