package com.tfg.supercomparator.application.service.carrefour;


import com.tfg.supercomparator.application.ports.in.carrefour.CarrefourSaveProductHistoryUseCase;
import com.tfg.supercomparator.application.ports.out.carrefour.CarrefourProductHistoryRepository;
import com.tfg.supercomparator.application.service.ProductService;
import com.tfg.supercomparator.domain.carrefour.CarrefourProductHistory;
import com.tfg.supercomparator.domain.carrefour.CarrefourProductHistoryItem;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.Collections;

@Service
public class CarrefourServiceImpl extends ProductService<CarrefourProductHistoryItem, CarrefourProductHistory, CarrefourProductHistoryRepository> implements CarrefourService {

    public CarrefourServiceImpl(@Qualifier("carrefourMongoRepository")CarrefourProductHistoryRepository repository) {
        super(repository);
    }

    @Override
    protected CarrefourProductHistory createNewProductHistory(CarrefourProductHistoryItem carrefourProductHistory) {
        return new CarrefourProductHistory(carrefourProductHistory.getNombre(), Collections.singletonMap(carrefourProductHistory.getFecha(), carrefourProductHistory.getPrice()));
    }
}