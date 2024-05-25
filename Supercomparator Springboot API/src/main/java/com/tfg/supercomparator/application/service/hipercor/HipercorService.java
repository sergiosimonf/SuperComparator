package com.tfg.supercomparator.application.service.hipercor;

import com.tfg.supercomparator.domain.hipercor.HipercorProduct;
import com.tfg.supercomparator.domain.hipercor.HipercorProductHistoryItem;
import com.tfg.supercomparator.domain.hipercor.HipercorProductHistoryItems;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface HipercorService {
    List<HipercorProduct> findProduct(String product);

    ResponseEntity<HipercorProductHistoryItem> saveProductHistory(HipercorProductHistoryItem hipercorProductHistory);

    ResponseEntity<HipercorProductHistoryItems> saveProductsHistory(HipercorProductHistoryItems hipercorProductsHistory);
}