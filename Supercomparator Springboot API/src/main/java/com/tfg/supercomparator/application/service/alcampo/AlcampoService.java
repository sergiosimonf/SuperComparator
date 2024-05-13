package com.tfg.supercomparator.application.service.alcampo;


import com.tfg.supercomparator.domain.alcampo.AlcampoProductHistory;
import com.tfg.supercomparator.domain.alcampo.AlcampoProductHistoryItem;
import org.springframework.http.ResponseEntity;

public interface AlcampoService {
    ResponseEntity<AlcampoProductHistoryItem> saveProductHistory(AlcampoProductHistoryItem alcampoProductHistory);
}