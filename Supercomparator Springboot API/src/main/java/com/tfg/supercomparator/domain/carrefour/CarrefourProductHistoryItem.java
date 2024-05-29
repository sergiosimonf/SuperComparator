package com.tfg.supercomparator.domain.carrefour;

import com.tfg.supercomparator.domain.ProductHistory;
import com.tfg.supercomparator.domain.ProductHistoryItem;
import com.tfg.supercomparator.domain.utils.BiMap;
import com.tfg.supercomparator.domain.utils.Fecha;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.springframework.data.annotation.Id;

import java.time.LocalDate;

@EqualsAndHashCode(callSuper = true)
public class CarrefourProductHistoryItem extends ProductHistoryItem {
    public CarrefourProductHistoryItem(String nombre, LocalDate fecha, Float price) {
        super(nombre, fecha, price);
    }

    public CarrefourProductHistoryItem() {
    }
}
