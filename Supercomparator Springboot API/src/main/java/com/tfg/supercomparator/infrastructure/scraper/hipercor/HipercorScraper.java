package com.tfg.supercomparator.infrastructure.scraper.hipercor;

import com.tfg.supercomparator.domain.hipercor.HipercorProduct;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public interface HipercorScraper {
    List<HipercorProduct> searchProduct(String productQuery);
}
