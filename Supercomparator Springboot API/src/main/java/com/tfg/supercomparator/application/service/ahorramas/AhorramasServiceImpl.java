package com.tfg.supercomparator.application.service.ahorramas;


import com.tfg.supercomparator.application.ports.out.ahorramas.AhorramasProductHistoryRepository;
import com.tfg.supercomparator.application.service.ProductService;
import com.tfg.supercomparator.domain.ItemProductHistory;
import com.tfg.supercomparator.domain.ahorramas.AhorramasProductHistory;
import com.tfg.supercomparator.domain.ahorramas.AhorramasProductHistoryItem;
import com.tfg.supercomparator.domain.ahorramas.AhorramasProductHistoryItems;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.Collections;

@Slf4j
@Service
public class AhorramasServiceImpl extends ProductService<AhorramasProductHistoryItem, AhorramasProductHistoryItems, AhorramasProductHistory, AhorramasProductHistoryRepository> implements AhorramasService {
    public AhorramasServiceImpl(@Qualifier("ahorramasMongoRepository") AhorramasProductHistoryRepository repository) {
        super(repository);
    }

    @Override
    protected AhorramasProductHistory instantiateNewProductHistory(AhorramasProductHistoryItem ahorramasProductHistory) {
        return new AhorramasProductHistory(ahorramasProductHistory.getName(), Collections.singletonMap(ahorramasProductHistory.getFecha(), ahorramasProductHistory.getPrice()));
    }

    @Override
    protected AhorramasProductHistoryItem convertToProductHistoryItem(ItemProductHistory item, AhorramasProductHistoryItems productHistory) {
        return new AhorramasProductHistoryItem(item.getNombre(), productHistory.getFecha(), item.getPrice());
    }
}