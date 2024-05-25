package com.tfg.supercomparator.application.ports.in.alcampo;

import com.tfg.supercomparator.domain.alcampo.AlcampoProductHistoryItem;
import com.tfg.supercomparator.domain.alcampo.AlcampoProductHistoryItems;
import org.springframework.http.ResponseEntity;

public interface AlacampoSaveProductHistoryUseCase {
    ResponseEntity<AlcampoProductHistoryItem> saveProductHistory(AlcampoProductHistoryItem alcampoProductHistory);

    ResponseEntity<AlcampoProductHistoryItems> saveProductsHistory(AlcampoProductHistoryItems alcampoProductHistory);
}
