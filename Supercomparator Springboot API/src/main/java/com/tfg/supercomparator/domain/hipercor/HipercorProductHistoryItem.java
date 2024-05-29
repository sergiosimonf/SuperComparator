package com.tfg.supercomparator.domain.hipercor;

import com.tfg.supercomparator.domain.ProductHistoryItem;
import com.tfg.supercomparator.domain.utils.BiMap;
import com.tfg.supercomparator.domain.utils.Fecha;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.springframework.data.annotation.Id;

import java.time.LocalDate;

@EqualsAndHashCode(callSuper = true)
public class HipercorProductHistoryItem extends ProductHistoryItem {
    public HipercorProductHistoryItem(String nombre, LocalDate fecha, Float price) {
        super(nombre, fecha, price);
    }

    public HipercorProductHistoryItem() {
    }
}
