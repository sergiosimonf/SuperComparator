package com.tfg.supercomparator.application.service.hipercor;

import com.tfg.supercomparator.domain.hipercor.HipercorProduct;
import com.tfg.supercomparator.domain.hipercor.HipercorProductHistory;
import com.tfg.supercomparator.domain.hipercor.HipercorProductHistoryItem;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface HipercorService {
    ResponseEntity<HipercorProductHistoryItem> saveProductHistory(HipercorProductHistoryItem hipercorProductHistory);

    List<HipercorProduct> findProduct(String product);
}