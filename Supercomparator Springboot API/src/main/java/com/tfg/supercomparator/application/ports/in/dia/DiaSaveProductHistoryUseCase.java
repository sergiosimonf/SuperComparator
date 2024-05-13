package com.tfg.supercomparator.application.ports.in.dia;


import com.tfg.supercomparator.domain.dia.DiaProductHistory;
import com.tfg.supercomparator.domain.dia.DiaProductHistoryItem;
import org.springframework.http.ResponseEntity;

public interface DiaSaveProductHistoryUseCase {
    ResponseEntity<DiaProductHistoryItem> saveProductHistory(DiaProductHistoryItem diaSaveProductHistory);

}
