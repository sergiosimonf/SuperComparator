package com.tfg.supercomparator.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public abstract class ProductHistoryItems {
    private LocalDate fecha;
    private List<ItemProductHistory> products;
}

