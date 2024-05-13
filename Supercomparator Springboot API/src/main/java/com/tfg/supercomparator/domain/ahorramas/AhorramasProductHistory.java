package com.tfg.supercomparator.domain.ahorramas;


import com.tfg.supercomparator.domain.ProductHistory;
import lombok.EqualsAndHashCode;

import java.time.LocalDate;
import java.util.Map;

@EqualsAndHashCode(callSuper = true)
public class AhorramasProductHistory extends ProductHistory {
    public AhorramasProductHistory(String nombre, Map<LocalDate, Double> localDateDoubleMap) {
        super(nombre, localDateDoubleMap);
    }

    public AhorramasProductHistory() {
        super();
    }
}
