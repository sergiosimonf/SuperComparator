package com.tfg.supercomparator.domain.alcampo;

import com.tfg.supercomparator.domain.ItemProductHistory;
import com.tfg.supercomparator.domain.ProductHistoryItems;
import lombok.EqualsAndHashCode;

import java.time.LocalDate;
import java.util.List;

@EqualsAndHashCode(callSuper = true)
public class AlcampoProductHistoryItems extends ProductHistoryItems {
    public AlcampoProductHistoryItems(LocalDate fecha, List<ItemProductHistory> products) {
        super(fecha, products);
    }

    public AlcampoProductHistoryItems() {
    }
}
