package com.tfg.supercomparator.domain.hipercor;

import com.tfg.supercomparator.domain.ProductHistory;
import com.tfg.supercomparator.domain.utils.BiMap;
import com.tfg.supercomparator.domain.utils.Fecha;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.springframework.data.annotation.Id;

import java.time.LocalDate;
import java.util.Map;

@EqualsAndHashCode(callSuper = true)
public class HipercorProductHistory extends ProductHistory {
    public HipercorProductHistory(String nombre, Map<LocalDate, Float> history) {
        super(nombre, history);
    }

    public HipercorProductHistory() {
    }
}
