package com.tfg.supercomparator.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;

import java.time.LocalDate;
import java.util.Map;

@Data
@AllArgsConstructor
@NoArgsConstructor
public abstract class ProductHistory {
    @Id
    private String nombre;
    private Map<LocalDate, Double> history;
}
