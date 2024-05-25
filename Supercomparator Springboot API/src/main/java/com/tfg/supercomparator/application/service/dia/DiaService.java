package com.tfg.supercomparator.application.service.dia;


import com.tfg.supercomparator.domain.dia.DiaProductHistoryItem;
import com.tfg.supercomparator.domain.dia.DiaProductHistoryItems;
import org.springframework.http.ResponseEntity;

public interface DiaService {
    ResponseEntity<DiaProductHistoryItem> saveProductHistory(DiaProductHistoryItem diaProductHistory);

    ResponseEntity<DiaProductHistoryItems> saveProductsHistory(DiaProductHistoryItems diaProductHistory);
}