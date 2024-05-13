package com.tfg.supercomparator.application.ports.in.hipercor;


import com.tfg.supercomparator.domain.hipercor.HipercorProductHistory;
import com.tfg.supercomparator.domain.hipercor.HipercorProductHistoryItem;
import org.springframework.http.ResponseEntity;

public interface HipercorSaveProductHistoryUseCase {
    ResponseEntity<HipercorProductHistoryItem> saveProductHistory(HipercorProductHistoryItem hipercorProduct);
}
