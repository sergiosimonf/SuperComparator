package com.tfg.supercomparator.infrastructure.scraper.mercadona;

import com.tfg.supercomparator.domain.mercadona.MercadonaProduct;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public interface MercadonaScraper {
    List<MercadonaProduct> searchProduct(String productQuery);
}
