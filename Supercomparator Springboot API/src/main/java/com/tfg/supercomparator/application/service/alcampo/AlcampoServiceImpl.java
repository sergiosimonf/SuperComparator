package com.tfg.supercomparator.application.service.alcampo;


import com.tfg.supercomparator.application.ports.out.ahorramas.AhorramasProductHistoryRepository;
import com.tfg.supercomparator.application.ports.out.alcampo.AlacampoProductHistoryRepository;
import com.tfg.supercomparator.application.service.ProductService;
import com.tfg.supercomparator.domain.ahorramas.AhorramasProductHistory;
import com.tfg.supercomparator.domain.alcampo.AlcampoProductHistory;
import com.tfg.supercomparator.domain.alcampo.AlcampoProductHistoryItem;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.Collections;

@Slf4j
@Service
public class AlcampoServiceImpl extends ProductService<AlcampoProductHistoryItem, AlcampoProductHistory, AlacampoProductHistoryRepository> implements AlcampoService {

    public AlcampoServiceImpl(@Qualifier("alcampoMongoRepository") AlacampoProductHistoryRepository repository) {
        super(repository);
    }

    @Override
    protected AlcampoProductHistory createNewProductHistory(AlcampoProductHistoryItem alcampoProductHistory) {
        return new AlcampoProductHistory(alcampoProductHistory.getNombre(), Collections.singletonMap(alcampoProductHistory.getFecha(), alcampoProductHistory.getPrice()));
    }
}