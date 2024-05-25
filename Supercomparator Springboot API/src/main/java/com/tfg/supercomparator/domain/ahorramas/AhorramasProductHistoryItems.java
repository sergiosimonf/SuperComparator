package com.tfg.supercomparator.domain.ahorramas;


import com.tfg.supercomparator.domain.ItemProductHistory;
import com.tfg.supercomparator.domain.ProductHistoryItems;
import lombok.EqualsAndHashCode;

import java.time.LocalDate;
import java.util.List;


@EqualsAndHashCode(callSuper = true)
public class AhorramasProductHistoryItems extends ProductHistoryItems {
    public AhorramasProductHistoryItems(LocalDate fecha, List<ItemProductHistory> products) {
        super(fecha, products);
    }

    public AhorramasProductHistoryItems() {
    }
}
