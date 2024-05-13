package com.tfg.supercomparator.application.service;

import com.tfg.supercomparator.domain.ProductHistory;
import com.tfg.supercomparator.domain.ProductHistoryItem;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.repository.CrudRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.Collections;
import java.util.Optional;

@Slf4j
@Service
@AllArgsConstructor
public abstract class ProductService<T extends ProductHistoryItem, U extends ProductHistory, S extends CrudRepository<U, String>> {
    private S repository;

    public ResponseEntity<T> saveProductHistory(T historicPrice) {
        if (historicPrice == null) {
            log.error("Se intentó guardar un historial de producto nulo.");
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }

        log.debug("Buscando producto con el id {}", historicPrice.getNombre());
        Optional<U> productHistoryOptional = repository.findById(historicPrice.getNombre());

        // Actualizar el historial del producto si existe
        if (productHistoryOptional.isPresent()) {
            U productHistory = productHistoryOptional.get();
            LocalDate latestDate = Collections.max(productHistory.getHistory().keySet());
            double lastPrice = productHistory.getHistory().getOrDefault(latestDate, Double.MIN_VALUE);

            // Si el precio más reciente es diferente al nuevo precio, se actualiza
            log.debug("Comparando último precio almacenado : {} con el del producto actual {}", lastPrice, historicPrice.getPrice());
            if (lastPrice != historicPrice.getPrice()) {
                log.debug("Actualizando precio en el historial del producto: {}", historicPrice);
                productHistory.getHistory().put(historicPrice.getFecha(), historicPrice.getPrice());
                repository.save(productHistory);
                return ResponseEntity.status(HttpStatus.OK).body(historicPrice);
            } else {
                // No hay cambios en el precio, no se realiza ninguna operación
                log.debug("El precio del producto no ha sido modificado desde el último registro, no se realizan cambios del producto: {}", historicPrice);
                return ResponseEntity.status(HttpStatus.NOT_MODIFIED).build();
            }

        } else {
            // Crear un nuevo historial del producto si no existe
            U newProductHistory = createNewProductHistory(historicPrice);
            repository.save(newProductHistory);
            return ResponseEntity.status(HttpStatus.CREATED).body(historicPrice);
        }
    }

    protected abstract U createNewProductHistory(T productHistory);
}
