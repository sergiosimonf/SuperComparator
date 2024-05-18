package com.tfg.supercomparator.application.service.ahorramas;


import com.tfg.supercomparator.application.ports.out.ahorramas.AhorramasProductHistoryRepository;
import com.tfg.supercomparator.application.service.ProductService;
import com.tfg.supercomparator.domain.ahorramas.AhorramasProductHistory;
import com.tfg.supercomparator.domain.ahorramas.AhorramasProductHistoryItem;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.Collections;

@Slf4j
@Service
public class AhorramasServiceImpl extends ProductService<AhorramasProductHistoryItem, AhorramasProductHistory, AhorramasProductHistoryRepository> implements AhorramasService {

    public AhorramasServiceImpl(@Qualifier("ahorramasMongoRepository") AhorramasProductHistoryRepository repository) {
        super(repository);
    }

    @Override
    protected AhorramasProductHistory createNewProductHistory(AhorramasProductHistoryItem ahorramasProductHistory) {
        return new AhorramasProductHistory(ahorramasProductHistory.getNombre(), Collections.singletonMap(ahorramasProductHistory.getFecha(), ahorramasProductHistory.getPrice()));
    }
}