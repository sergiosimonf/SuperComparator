package com.tfg.supercomparator.domain.hipercor;

import com.tfg.supercomparator.domain.ItemProductHistory;
import com.tfg.supercomparator.domain.ProductHistoryItems;
import lombok.EqualsAndHashCode;

import java.time.LocalDate;
import java.util.List;

@EqualsAndHashCode(callSuper = true)
public class HipercorProductHistoryItems extends ProductHistoryItems {
    public HipercorProductHistoryItems(LocalDate fecha, List<ItemProductHistory> products) {
        super(fecha, products);
    }

    public HipercorProductHistoryItems() {
    }
}
