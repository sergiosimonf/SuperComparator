package com.tfg.supercomparator.application.service.alcampo;


import com.tfg.supercomparator.application.ports.out.ahorramas.AhorramasProductHistoryRepository;
import com.tfg.supercomparator.application.ports.out.alcampo.AlacampoProductHistoryRepository;
import com.tfg.supercomparator.application.service.ProductService;
import com.tfg.supercomparator.domain.ahorramas.AhorramasProductHistory;
import com.tfg.supercomparator.domain.alcampo.AlcampoProductHistory;
import com.tfg.supercomparator.domain.alcampo.AlcampoProductHistoryItem;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.Collections;

@Slf4j
@Service
public class AlcampoServiceImpl extends ProductService<AlcampoProductHistoryItem, AlcampoProductHistory, AlacampoProductHistoryRepository> implements AlcampoService {

    public AlcampoServiceImpl(@Qualifier("alcampoMongoRepository") AlacampoProductHistoryRepository repository) {
        super(repository);
    }

    @Override
    protected AlcampoProductHistory createNewProductHistory(AlcampoProductHistoryItem alcampoProductHistory) {
        return new AlcampoProductHistory(alcampoProductHistory.getNombre(), Collections.singletonMap(alcampoProductHistory.getFecha(), alcampoProductHistory.getPrice()));
    }
}

//@Override
//public ResponseEntity<AlcampoProductHistoryItem> saveProductHistory(AlcampoProductHistoryItem alcampoProductHistory) {
//    log.debug("Buscando producto con el id {}", alcampoProductHistory.getNombre());
//    Optional<AlcampoProductHistory> productHistoryOptional = alacampoProductHistoryRepository.findById(alcampoProductHistory.getNombre());
//
//    // Actualizar el historial del producto si existe
//    if (productHistoryOptional.isPresent()) {
//        AlcampoProductHistory productHistory = productHistoryOptional.get();
//        LocalDate latestDate = Collections.max(productHistory.getHistory().keySet());
//        double lastPrice = productHistory.getHistory().getOrDefault(latestDate, Double.MIN_VALUE);
//
//        // Si el precio más reciente es diferente al nuevo precio, se actualiza
//        log.debug("Comparando ultimo precio almacenado : {} con el del producto actual {}", lastPrice, alcampoProductHistory.getPrice());
//        if (lastPrice != alcampoProductHistory.getPrice()) {
//            log.debug("El actualizando precio en el histrial del producto: {}", alcampoProductHistory);
//            productHistory.getHistory().put(alcampoProductHistory.getFecha(), alcampoProductHistory.getPrice());
//            alacampoProductHistoryRepository.save(productHistory);
//            return ResponseEntity.status(HttpStatus.OK).body(alcampoProductHistory);
//        } else {
//            // No hay cambios en el precio, no se realiza ninguna operación
//            log.debug("El precio del producto no ha sido modificado desde el último registro no se realizan cambios del producto: {}", alcampoProductHistory);
//            return ResponseEntity.status(HttpStatus.NOT_MODIFIED).build();
//        }
//
//    } else {
//        // Crear un nuevo historial del producto si no existe
//        AlcampoProductHistory newProductHistory = AlcampoProductHistory.builder()
//                .nombre(alcampoProductHistory.getNombre())
//                .history(Collections.singletonMap(alcampoProductHistory.getFecha(), alcampoProductHistory.getPrice()))
//                .build();
//        alacampoProductHistoryRepository.save(newProductHistory);
//        return ResponseEntity.status(HttpStatus.CREATED).body(alcampoProductHistory);
//    }
//}