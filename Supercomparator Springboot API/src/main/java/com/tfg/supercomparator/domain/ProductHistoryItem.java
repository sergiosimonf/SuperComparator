package com.tfg.supercomparator.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.Map;

@Data
@AllArgsConstructor
@NoArgsConstructor
public abstract class ProductHistoryItem {
    private String nombre;
    private LocalDate fecha;
    private double price;
}
