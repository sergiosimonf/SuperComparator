package com.tfg.supercomparator.domain.dia;

import com.tfg.supercomparator.domain.ProductHistoryItem;
import com.tfg.supercomparator.domain.utils.BiMap;
import com.tfg.supercomparator.domain.utils.Fecha;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.springframework.data.annotation.Id;

import java.time.LocalDate;

@EqualsAndHashCode(callSuper = true)
public class DiaProductHistoryItem extends ProductHistoryItem {
    public DiaProductHistoryItem(String nombre, LocalDate fecha, double price) {
        super(nombre, fecha, price);
    }

    public DiaProductHistoryItem() {
    }
}
