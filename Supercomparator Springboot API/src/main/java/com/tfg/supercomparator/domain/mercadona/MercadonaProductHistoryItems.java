package com.tfg.supercomparator.domain.mercadona;

import com.tfg.supercomparator.domain.ItemProductHistory;
import com.tfg.supercomparator.domain.ProductHistoryItems;
import lombok.EqualsAndHashCode;

import java.time.LocalDate;
import java.util.List;

@EqualsAndHashCode(callSuper = true)
public class MercadonaProductHistoryItems extends ProductHistoryItems {
    public MercadonaProductHistoryItems(LocalDate fecha, List<ItemProductHistory> products) {
        super(fecha, products);
    }

    public MercadonaProductHistoryItems() {
    }
}
