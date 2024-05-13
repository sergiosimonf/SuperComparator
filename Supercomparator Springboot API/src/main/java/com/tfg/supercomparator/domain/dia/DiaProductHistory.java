package com.tfg.supercomparator.domain.dia;

import com.tfg.supercomparator.domain.ProductHistory;
import com.tfg.supercomparator.domain.ProductHistoryItem;
import com.tfg.supercomparator.domain.utils.BiMap;
import com.tfg.supercomparator.domain.utils.Fecha;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.springframework.data.annotation.Id;

import java.time.LocalDate;
import java.util.Map;

@EqualsAndHashCode(callSuper = true)
public class DiaProductHistory extends ProductHistory {
    public DiaProductHistory(String nombre, Map<LocalDate, Double> history) {
        super(nombre, history);
    }

    public DiaProductHistory() {
    }
}
