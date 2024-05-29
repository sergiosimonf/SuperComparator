package com.tfg.supercomparator.domain.mercadona;

import com.tfg.supercomparator.domain.ProductHistoryItem;
import com.tfg.supercomparator.domain.utils.Fecha;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@EqualsAndHashCode(callSuper = true)
public class MercadonaProductHistoryItem extends ProductHistoryItem {
    public MercadonaProductHistoryItem(String nombre, LocalDate fecha, Float price) {
        super(nombre, fecha, price);
    }

    public MercadonaProductHistoryItem() {
    }
}
