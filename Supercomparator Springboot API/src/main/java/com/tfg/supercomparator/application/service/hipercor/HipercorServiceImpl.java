package com.tfg.supercomparator.application.service.hipercor;

import com.tfg.supercomparator.application.ports.out.hipercor.HipercorProductHistoryRepository;
import com.tfg.supercomparator.application.service.ProductService;
import com.tfg.supercomparator.domain.hipercor.HipercorProduct;
import com.tfg.supercomparator.domain.hipercor.HipercorProductHistory;
import com.tfg.supercomparator.domain.hipercor.HipercorProductHistoryItem;
import com.tfg.supercomparator.infrastructure.repository.mongo.hipercor.HipercorMongoRepository;
import com.tfg.supercomparator.infrastructure.scraper.hipercor.HipercorScraper;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;

@Service
public class HipercorServiceImpl extends ProductService<HipercorProductHistoryItem, HipercorProductHistory, HipercorProductHistoryRepository> implements HipercorService {

    private final HipercorScraper hipercorScraper;

    public HipercorServiceImpl(@Qualifier("hipercorMongoRepository") HipercorProductHistoryRepository repository, HipercorScraper hipercorScraper) {
        super(repository);
        this.hipercorScraper = hipercorScraper;
    }

    @Override
    public List<HipercorProduct> findProduct(String product) {
        return hipercorScraper.searchProduct(product);
    }

    @Override
    protected HipercorProductHistory createNewProductHistory(HipercorProductHistoryItem hipercorProductHistory) {
        return new HipercorProductHistory(hipercorProductHistory.getNombre(), Collections.singletonMap(hipercorProductHistory.getFecha(), hipercorProductHistory.getPrice()));
    }
}