package com.tfg.supercomparator.application.service.dia;

import com.tfg.supercomparator.application.ports.out.dia.DiaProductHistoryRepository;
import com.tfg.supercomparator.application.service.ProductService;
import com.tfg.supercomparator.domain.ItemProductHistory;
import com.tfg.supercomparator.domain.dia.DiaProductHistory;
import com.tfg.supercomparator.domain.dia.DiaProductHistoryItem;
import com.tfg.supercomparator.domain.dia.DiaProductHistoryItems;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.Collections;

@Service
public class DiaServiceImpl extends ProductService<DiaProductHistoryItem, DiaProductHistoryItems, DiaProductHistory, DiaProductHistoryRepository> implements DiaService {

    public DiaServiceImpl(@Qualifier("diaMongoRepository") DiaProductHistoryRepository repository) {
        super(repository);
    }

    @Override
    protected DiaProductHistory instantiateNewProductHistory(DiaProductHistoryItem diaProductHistory) {
        return new DiaProductHistory(diaProductHistory.getName(), Collections.singletonMap(diaProductHistory.getFecha(), diaProductHistory.getPrice()));
    }

    @Override
    protected DiaProductHistoryItem convertToProductHistoryItem(ItemProductHistory item, DiaProductHistoryItems productHistory) {
        return new DiaProductHistoryItem(item.getNombre(), productHistory.getFecha(), item.getPrice());
    }
}