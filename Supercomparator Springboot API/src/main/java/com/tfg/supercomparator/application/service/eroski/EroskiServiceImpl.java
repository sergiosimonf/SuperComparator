package com.tfg.supercomparator.application.service.eroski;


import com.tfg.supercomparator.application.ports.out.eroski.EroskiProductHistoryRepository;
import com.tfg.supercomparator.application.service.ProductService;
import com.tfg.supercomparator.domain.ItemProductHistory;
import com.tfg.supercomparator.domain.eroski.EroskiProductHistory;
import com.tfg.supercomparator.domain.eroski.EroskiProductHistoryItem;
import com.tfg.supercomparator.domain.eroski.EroskiProductHistoryItems;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.Collections;

@Service
public class EroskiServiceImpl extends ProductService<EroskiProductHistoryItem, EroskiProductHistoryItems, EroskiProductHistory, EroskiProductHistoryRepository> implements EroskiService {
    public EroskiServiceImpl(@Qualifier("eroskiMongoRepository")EroskiProductHistoryRepository repository) {
        super(repository);
    }

    @Override
    protected EroskiProductHistory instantiateNewProductHistory(EroskiProductHistoryItem eroskiProductHistory) {
        return new EroskiProductHistory(eroskiProductHistory.getName(), Collections.singletonMap(eroskiProductHistory.getFecha(), eroskiProductHistory.getPrice()));
    }

    @Override
    protected EroskiProductHistoryItem convertToProductHistoryItem(ItemProductHistory item, EroskiProductHistoryItems productHistory) {
        return new EroskiProductHistoryItem(item.getNombre(), productHistory.getFecha(), item.getPrice());
    }
}