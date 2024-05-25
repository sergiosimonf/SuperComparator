package com.tfg.supercomparator.application.usecases.mercadona;


import com.tfg.supercomparator.application.ports.in.mercadona.MercadonaSaveProductHistoryUseCase;
import com.tfg.supercomparator.application.service.mercadona.MercadonaService;
import com.tfg.supercomparator.domain.mercadona.MercadonaProductHistoryItem;
import com.tfg.supercomparator.domain.mercadona.MercadonaProductHistoryItems;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

@Component
@AllArgsConstructor
public class MercadonaSaveProductHistoryUseCaseImpl implements MercadonaSaveProductHistoryUseCase {

    private MercadonaService mercadonaService;

    @Override
    public ResponseEntity<MercadonaProductHistoryItem> saveProductHistory(MercadonaProductHistoryItem mercadonaProductHistory) {
        return mercadonaService.saveProductHistory(mercadonaProductHistory);
    }

    @Override
    public ResponseEntity<MercadonaProductHistoryItems> saveProductsHistory(MercadonaProductHistoryItems mercadonaProductHistory) {
        return mercadonaService.saveProductsHistory(mercadonaProductHistory);
    }
}