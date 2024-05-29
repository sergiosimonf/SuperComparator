package com.tfg.supercomparator.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
public abstract class ProductHistoryItem {
    private String name;
    private LocalDate fecha;
    private Float price;
}
