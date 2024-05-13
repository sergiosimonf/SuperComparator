package com.tfg.supercomparator.application.service.eroski;


import com.tfg.supercomparator.application.ports.out.ahorramas.AhorramasProductHistoryRepository;
import com.tfg.supercomparator.application.ports.out.eroski.EroskiProductHistoryRepository;
import com.tfg.supercomparator.application.service.ProductService;
import com.tfg.supercomparator.domain.ahorramas.AhorramasProductHistory;
import com.tfg.supercomparator.domain.eroski.EroskiProductHistory;
import com.tfg.supercomparator.domain.eroski.EroskiProductHistoryItem;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.Collections;

@Service
public class EroskiServiceImpl extends ProductService<EroskiProductHistoryItem, EroskiProductHistory, EroskiProductHistoryRepository> implements EroskiService {
    public EroskiServiceImpl(@Qualifier("eroskiMongoRepository")EroskiProductHistoryRepository repository) {
        super(repository);
    }

    @Override
    protected EroskiProductHistory createNewProductHistory(EroskiProductHistoryItem eroskiProductHistory) {
        return new EroskiProductHistory(eroskiProductHistory.getNombre(), Collections.singletonMap(eroskiProductHistory.getFecha(), eroskiProductHistory.getPrice()));
    }
}