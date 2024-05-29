package com.tfg.supercomparator.domain.ahorramas;


import com.tfg.supercomparator.domain.ProductHistoryItem;
import com.tfg.supercomparator.domain.utils.BiMap;
import com.tfg.supercomparator.domain.utils.Fecha;
import lombok.*;
import org.springframework.data.annotation.Id;

import java.time.LocalDate;


@EqualsAndHashCode(callSuper = true)
public class AhorramasProductHistoryItem extends ProductHistoryItem {
    public AhorramasProductHistoryItem(String nombre, LocalDate fecha, Float price) {
        super(nombre, fecha, price);
    }

    public AhorramasProductHistoryItem() {
    }
}
