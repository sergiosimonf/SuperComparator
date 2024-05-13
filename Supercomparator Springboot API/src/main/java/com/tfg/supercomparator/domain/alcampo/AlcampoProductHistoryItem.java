package com.tfg.supercomparator.domain.alcampo;

import com.tfg.supercomparator.domain.ProductHistoryItem;
import com.tfg.supercomparator.domain.utils.BiMap;
import com.tfg.supercomparator.domain.utils.Fecha;
import lombok.*;
import org.springframework.data.annotation.Id;

import java.time.LocalDate;

@EqualsAndHashCode(callSuper = true)
public class AlcampoProductHistoryItem extends ProductHistoryItem {
    public AlcampoProductHistoryItem(String nombre, LocalDate fecha, double price) {
        super(nombre, fecha, price);
    }

    public AlcampoProductHistoryItem() {
    }
}
