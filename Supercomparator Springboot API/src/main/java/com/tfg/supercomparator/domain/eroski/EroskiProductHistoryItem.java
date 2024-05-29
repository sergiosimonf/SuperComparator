package com.tfg.supercomparator.domain.eroski;


import com.tfg.supercomparator.domain.ProductHistoryItem;
import com.tfg.supercomparator.domain.utils.BiMap;
import com.tfg.supercomparator.domain.utils.Fecha;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.springframework.data.annotation.Id;

import java.time.LocalDate;

@EqualsAndHashCode(callSuper = true)
public class EroskiProductHistoryItem extends ProductHistoryItem {
    public EroskiProductHistoryItem(String nombre, LocalDate fecha, Float price) {
        super(nombre, fecha, price);
    }

    public EroskiProductHistoryItem() {
    }
}
