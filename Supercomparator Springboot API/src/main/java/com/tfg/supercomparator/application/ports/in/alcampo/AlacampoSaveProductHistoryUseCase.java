package com.tfg.supercomparator.application.ports.in.alcampo;

import com.tfg.supercomparator.domain.alcampo.AlcampoProductHistory;
import com.tfg.supercomparator.domain.alcampo.AlcampoProductHistoryItem;
import org.springframework.http.ResponseEntity;

public interface AlacampoSaveProductHistoryUseCase {
    ResponseEntity<AlcampoProductHistoryItem> saveProductHistory(AlcampoProductHistoryItem alcampoProductHistory);
}
