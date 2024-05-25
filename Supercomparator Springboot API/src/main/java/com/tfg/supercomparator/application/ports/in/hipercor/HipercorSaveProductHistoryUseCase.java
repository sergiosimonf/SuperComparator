package com.tfg.supercomparator.application.ports.in.hipercor;


import com.tfg.supercomparator.domain.hipercor.HipercorProductHistoryItem;
import com.tfg.supercomparator.domain.hipercor.HipercorProductHistoryItems;
import org.springframework.http.ResponseEntity;

public interface HipercorSaveProductHistoryUseCase {
    ResponseEntity<HipercorProductHistoryItem> saveProductHistory(HipercorProductHistoryItem hipercorProduct);

    ResponseEntity<HipercorProductHistoryItems> saveProductsHistory(HipercorProductHistoryItems hipercorProducts);
}
