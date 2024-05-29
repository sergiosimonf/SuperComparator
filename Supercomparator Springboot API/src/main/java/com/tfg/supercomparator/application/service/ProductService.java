package com.tfg.supercomparator.application.service;

import com.tfg.supercomparator.domain.ItemProductHistory;
import com.tfg.supercomparator.domain.ProductHistory;
import com.tfg.supercomparator.domain.ProductHistoryItem;
import com.tfg.supercomparator.domain.ProductHistoryItems;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.repository.CrudRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Slf4j
@Service
@AllArgsConstructor
public abstract class ProductService<T extends ProductHistoryItem, Y extends ProductHistoryItems, U extends ProductHistory, S extends CrudRepository<U, String>> {

    private final S repository;

    public ResponseEntity<T> saveProductHistory(T historicPrice) {
        if (historicPrice == null) {
            log.error("Attempted to save a null product history.");
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }

        log.debug("Searching for product with id {}", historicPrice.getName());
        Optional<U> productHistoryOptional = repository.findById(historicPrice.getName());

        if (productHistoryOptional.isPresent()) {
            return updateExistingProductHistory(historicPrice, productHistoryOptional.get());
        } else {
            return createNewProductHistory(historicPrice);
        }
    }

    public ResponseEntity<Y> saveProductsHistory(Y historicPrices) {
        if (historicPrices == null) {
            log.error("Attempted to save a null list of product histories.");
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }

        List<T> listProductHistoryItem = createListProductHistoryItem(historicPrices);
        listProductHistoryItem.parallelStream().forEach(this::saveProductHistory);

        return ResponseEntity.status(HttpStatus.OK).body(historicPrices);
    }

    private ResponseEntity<T> updateExistingProductHistory(T historicPrice, U productHistory) {
        LocalDate latestDate = Collections.max(productHistory.getHistory().keySet());
        double lastPrice = productHistory.getHistory().getOrDefault(latestDate, Float.MIN_VALUE);

        log.debug("Comparing last stored price: {} with the current product price {}", lastPrice, historicPrice.getPrice());
        if (lastPrice != historicPrice.getPrice()) {
            log.debug("Updating product history price: {}", historicPrice);
            productHistory.getHistory().put(historicPrice.getFecha(), historicPrice.getPrice());
            repository.save(productHistory);
            return ResponseEntity.status(HttpStatus.OK).body(historicPrice);
        } else {
            log.debug("Product price has not changed since the last record, no updates made: {}", historicPrice);
            return ResponseEntity.status(HttpStatus.NOT_MODIFIED).build();
        }
    }

    private ResponseEntity<T> createNewProductHistory(T historicPrice) {
        U newProductHistory = instantiateNewProductHistory(historicPrice);
        repository.save(newProductHistory);
        return ResponseEntity.status(HttpStatus.CREATED).body(historicPrice);
    }

    protected abstract U instantiateNewProductHistory(T productHistory);


    protected abstract T convertToProductHistoryItem(ItemProductHistory item, Y productHistory);

    protected List<T> createListProductHistoryItem(Y productHistory) {
        return productHistory.getProducts().stream()
                .map(item -> convertToProductHistoryItem(item, productHistory))
                .collect(Collectors.toList());
    }
}
