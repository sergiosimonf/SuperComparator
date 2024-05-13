package com.tfg.supercomparator.domain.utils;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.jetbrains.annotations.NotNull;

import java.time.LocalDate;
import java.util.List;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Fecha {
    private int dia = LocalDate.now().getDayOfMonth();
    private int mes = LocalDate.now().getMonthValue();
    private int ano = LocalDate.now().getYear();

    public static Fecha obtenerMasReciente(List<String> fechas) {
        LocalDate fechaMasReciente = null;

        for (String fechaStr : fechas) {
            LocalDate fecha = convertirAFecha(fechaStr);
            if (fecha != null && (fechaMasReciente == null || fecha.isAfter(fechaMasReciente))) {
                fechaMasReciente = fecha;
            }
        }

        if (fechaMasReciente != null) {
        } else {
            System.out.println("No se encontraron fechas válidas en la lista.");
        }
        return Fecha.builder().build();
    }


    public static LocalDate convertirAFecha(String fechaStr) {
        try {
            // Extrayendo los valores de día, mes y año del string JSON
            int dia = Integer.parseInt(fechaStr.substring(fechaStr.indexOf(":") + 1, fechaStr.indexOf(",")));
            int mes = Integer.parseInt(fechaStr.substring(fechaStr.lastIndexOf(":") + 1, fechaStr.lastIndexOf(",")));
            int ano = Integer.parseInt(fechaStr.substring(fechaStr.lastIndexOf(":") + 1, fechaStr.lastIndexOf("}")));

            // Creando el objeto LocalDate
            return LocalDate.of(ano, mes, dia);
        } catch (Exception e) {
            // Manejar la excepción si el formato no es válido
            System.out.println("Error al convertir la fecha: " + fechaStr);
            return null;
        }
    }

    @Override
    public String toString() {
        return String.format("%02d/%02d/%d", dia, mes, ano);
    }
}
