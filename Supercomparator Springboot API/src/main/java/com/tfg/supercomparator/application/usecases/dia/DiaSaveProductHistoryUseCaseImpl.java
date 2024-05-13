package com.tfg.supercomparator.application.usecases.dia;

import com.tfg.supercomparator.application.ports.in.dia.DiaSaveProductHistoryUseCase;
import com.tfg.supercomparator.application.service.dia.DiaService;
import com.tfg.supercomparator.domain.dia.DiaProductHistory;
import com.tfg.supercomparator.domain.dia.DiaProductHistoryItem;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

@Component
@AllArgsConstructor
public class DiaSaveProductHistoryUseCaseImpl implements DiaSaveProductHistoryUseCase {

    private DiaService diaService;

    @Override
    public ResponseEntity<DiaProductHistoryItem> saveProductHistory(DiaProductHistoryItem diaProductHistory) {
        return diaService.saveProductHistory(diaProductHistory);
    }
}