package com.tfg.supercomparator.domain.dia;

import com.tfg.supercomparator.domain.ItemProductHistory;
import com.tfg.supercomparator.domain.ProductHistoryItems;
import lombok.EqualsAndHashCode;

import java.time.LocalDate;
import java.util.List;

@EqualsAndHashCode(callSuper = true)
public class DiaProductHistoryItems extends ProductHistoryItems {
    public DiaProductHistoryItems(LocalDate fecha, List<ItemProductHistory> products) {
        super(fecha, products);
    }

    public DiaProductHistoryItems() {
    }
}
