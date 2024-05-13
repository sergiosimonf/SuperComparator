package com.tfg.supercomparator.domain.carrefour;

import com.tfg.supercomparator.domain.ProductHistory;
import lombok.EqualsAndHashCode;
import org.springframework.data.annotation.Id;

import java.time.LocalDate;
import java.util.Map;

@EqualsAndHashCode(callSuper = true)
public class CarrefourProductHistory extends ProductHistory {
    public CarrefourProductHistory(String nombre, Map<LocalDate, Double> history) {
        super(nombre, history);
    }

    public CarrefourProductHistory() {
    }
}
