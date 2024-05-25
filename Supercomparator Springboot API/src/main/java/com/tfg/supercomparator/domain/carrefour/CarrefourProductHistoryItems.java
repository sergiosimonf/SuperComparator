package com.tfg.supercomparator.domain.carrefour;

import com.tfg.supercomparator.domain.ItemProductHistory;
import com.tfg.supercomparator.domain.ProductHistoryItems;
import lombok.EqualsAndHashCode;

import java.time.LocalDate;
import java.util.List;

@EqualsAndHashCode(callSuper = true)
public class CarrefourProductHistoryItems extends ProductHistoryItems {
    public CarrefourProductHistoryItems(LocalDate fecha, List<ItemProductHistory> products) {
        super(fecha, products);
    }

    public CarrefourProductHistoryItems() {
    }
}
