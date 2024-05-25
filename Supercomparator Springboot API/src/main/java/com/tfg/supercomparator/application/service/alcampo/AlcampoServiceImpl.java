package com.tfg.supercomparator.application.service.alcampo;


import com.tfg.supercomparator.application.ports.out.alcampo.AlacampoProductHistoryRepository;
import com.tfg.supercomparator.application.service.ProductService;
import com.tfg.supercomparator.domain.ItemProductHistory;
import com.tfg.supercomparator.domain.alcampo.AlcampoProductHistory;
import com.tfg.supercomparator.domain.alcampo.AlcampoProductHistoryItem;
import com.tfg.supercomparator.domain.alcampo.AlcampoProductHistoryItems;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.Collections;

@Slf4j
@Service
public class AlcampoServiceImpl extends ProductService<AlcampoProductHistoryItem, AlcampoProductHistoryItems, AlcampoProductHistory, AlacampoProductHistoryRepository> implements AlcampoService {

    public AlcampoServiceImpl(@Qualifier("alcampoMongoRepository") AlacampoProductHistoryRepository repository) {
        super(repository);
    }

    @Override
    protected AlcampoProductHistory instantiateNewProductHistory(AlcampoProductHistoryItem alcampoProductHistory) {
        return new AlcampoProductHistory(alcampoProductHistory.getName(), Collections.singletonMap(alcampoProductHistory.getFecha(), alcampoProductHistory.getPrice()));
    }

    @Override
    protected AlcampoProductHistoryItem convertToProductHistoryItem(ItemProductHistory item, AlcampoProductHistoryItems productHistory) {
        return new AlcampoProductHistoryItem(item.getNombre(), productHistory.getFecha(), item.getPrice());
    }
}