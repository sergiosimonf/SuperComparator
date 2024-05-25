package com.tfg.supercomparator.domain.eroski;


import com.tfg.supercomparator.domain.ItemProductHistory;
import com.tfg.supercomparator.domain.ProductHistoryItems;
import lombok.EqualsAndHashCode;

import java.time.LocalDate;
import java.util.List;

@EqualsAndHashCode(callSuper = true)
public class EroskiProductHistoryItems extends ProductHistoryItems {
    public EroskiProductHistoryItems(LocalDate fecha, List<ItemProductHistory> products) {
        super(fecha, products);
    }

    public EroskiProductHistoryItems() {
    }
}
