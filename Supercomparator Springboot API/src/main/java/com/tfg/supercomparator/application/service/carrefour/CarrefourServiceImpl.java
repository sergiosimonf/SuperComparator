package com.tfg.supercomparator.application.service.carrefour;


import com.tfg.supercomparator.application.ports.out.carrefour.CarrefourProductHistoryRepository;
import com.tfg.supercomparator.application.service.ProductService;
import com.tfg.supercomparator.domain.ItemProductHistory;
import com.tfg.supercomparator.domain.carrefour.CarrefourProductHistory;
import com.tfg.supercomparator.domain.carrefour.CarrefourProductHistoryItem;
import com.tfg.supercomparator.domain.carrefour.CarrefourProductHistoryItems;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.Collections;

@Service
public class CarrefourServiceImpl extends ProductService<CarrefourProductHistoryItem, CarrefourProductHistoryItems, CarrefourProductHistory, CarrefourProductHistoryRepository> implements CarrefourService {

    public CarrefourServiceImpl(@Qualifier("carrefourMongoRepository")CarrefourProductHistoryRepository repository) {
        super(repository);
    }

    @Override
    protected CarrefourProductHistory instantiateNewProductHistory(CarrefourProductHistoryItem carrefourProductHistory) {
        return new CarrefourProductHistory(carrefourProductHistory.getName(), Collections.singletonMap(carrefourProductHistory.getFecha(), carrefourProductHistory.getPrice()));
    }

    @Override
    protected CarrefourProductHistoryItem convertToProductHistoryItem(ItemProductHistory item, CarrefourProductHistoryItems productHistory) {
        return new CarrefourProductHistoryItem(item.getNombre(), productHistory.getFecha(), item.getPrice());
    }
}