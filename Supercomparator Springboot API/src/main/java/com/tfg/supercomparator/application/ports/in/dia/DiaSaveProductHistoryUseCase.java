package com.tfg.supercomparator.application.ports.in.dia;


import com.tfg.supercomparator.domain.dia.DiaProductHistoryItem;
import com.tfg.supercomparator.domain.dia.DiaProductHistoryItems;
import org.springframework.http.ResponseEntity;

public interface DiaSaveProductHistoryUseCase {
    ResponseEntity<DiaProductHistoryItem> saveProductHistory(DiaProductHistoryItem diaSaveProductHistory);

    ResponseEntity<DiaProductHistoryItems> saveProductsHistory(DiaProductHistoryItems diaSaveProductHistory);
}
