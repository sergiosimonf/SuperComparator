package com.tfg.supercomparator.application.service.mercadona;

import com.tfg.supercomparator.application.ports.out.mercadona.MercadonaProductHistoryRepository;
import com.tfg.supercomparator.application.service.ProductService;
import com.tfg.supercomparator.domain.ahorramas.AhorramasProductHistory;
import com.tfg.supercomparator.domain.mercadona.MercadonaProduct;
import com.tfg.supercomparator.domain.mercadona.MercadonaProductHistory;
import com.tfg.supercomparator.domain.mercadona.MercadonaProductHistoryItem;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;

@Service
public class MercadonaServiceImpl extends ProductService<MercadonaProductHistoryItem, MercadonaProductHistory, MercadonaProductHistoryRepository> implements MercadonaService {

    public MercadonaServiceImpl(@Qualifier("mercadonaMongoRepository") MercadonaProductHistoryRepository repository) {
        super(repository);
    }

    @Override
    public List<MercadonaProduct> findProduct(String product) {
        return null;
    }

    @Override
    protected MercadonaProductHistory createNewProductHistory(MercadonaProductHistoryItem mercadonaProductHistory) {
        return new MercadonaProductHistory(mercadonaProductHistory.getNombre(), Collections.singletonMap(mercadonaProductHistory.getFecha(), mercadonaProductHistory.getPrice()));
    }
}