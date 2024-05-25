package com.tfg.supercomparator.application.usecases.hipercor;


import com.tfg.supercomparator.application.ports.in.hipercor.HipercorSaveProductHistoryUseCase;
import com.tfg.supercomparator.application.service.hipercor.HipercorService;
import com.tfg.supercomparator.domain.hipercor.HipercorProductHistoryItem;
import com.tfg.supercomparator.domain.hipercor.HipercorProductHistoryItems;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

@Component
@AllArgsConstructor
public class HipercorSaveProductHistoryUseCaseImpl implements HipercorSaveProductHistoryUseCase {

    private HipercorService hipercorService;

    @Override
    public ResponseEntity<HipercorProductHistoryItem> saveProductHistory(HipercorProductHistoryItem hipercorProduct) {
        return hipercorService.saveProductHistory(hipercorProduct);
    }

    @Override
    public ResponseEntity<HipercorProductHistoryItems> saveProductsHistory(HipercorProductHistoryItems hipercorProduct) {
        return hipercorService.saveProductsHistory(hipercorProduct);
    }
}