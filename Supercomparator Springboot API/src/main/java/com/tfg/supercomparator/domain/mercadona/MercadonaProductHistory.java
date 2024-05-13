package com.tfg.supercomparator.domain.mercadona;

import com.tfg.supercomparator.domain.ProductHistory;
import com.tfg.supercomparator.domain.utils.BiMap;
import com.tfg.supercomparator.domain.utils.Fecha;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.springframework.data.annotation.Id;

import java.time.LocalDate;
import java.util.Map;

@EqualsAndHashCode(callSuper = true)
public class MercadonaProductHistory extends ProductHistory {
    public MercadonaProductHistory(String nombre, Map<LocalDate, Double> history) {
        super(nombre, history);
    }

    public MercadonaProductHistory() {
    }
}
