package com.tfg.supercomparator.application.service.dia;


import com.tfg.supercomparator.domain.dia.DiaProductHistory;
import com.tfg.supercomparator.domain.dia.DiaProductHistoryItem;
import org.springframework.http.ResponseEntity;

public interface DiaService {
    ResponseEntity<DiaProductHistoryItem> saveProductHistory(DiaProductHistoryItem diaProductHistory);
}