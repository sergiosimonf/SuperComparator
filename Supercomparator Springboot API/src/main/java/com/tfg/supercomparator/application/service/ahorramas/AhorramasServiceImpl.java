package com.tfg.supercomparator.application.service.ahorramas;


import com.tfg.supercomparator.application.ports.out.ahorramas.AhorramasProductHistoryRepository;
import com.tfg.supercomparator.application.service.ProductService;
import com.tfg.supercomparator.domain.ahorramas.AhorramasProductHistory;
import com.tfg.supercomparator.domain.ahorramas.AhorramasProductHistoryItem;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.Collections;

@Slf4j
@Service
public class AhorramasServiceImpl extends ProductService<AhorramasProductHistoryItem, AhorramasProductHistory, AhorramasProductHistoryRepository> implements AhorramasService {

//    ahorramasMongoRepository
    public AhorramasServiceImpl(@Qualifier("ahorramasMongoRepository") AhorramasProductHistoryRepository repository) {
        super(repository);
    }

    @Override
    protected AhorramasProductHistory createNewProductHistory(AhorramasProductHistoryItem ahorramasProductHistory) {
        return new AhorramasProductHistory(ahorramasProductHistory.getNombre(), Collections.singletonMap(ahorramasProductHistory.getFecha(), ahorramasProductHistory.getPrice()));
    }
}

//@Slf4j
//@Service
//@AllArgsConstructor
//public class AhorramasServiceImpl implements AhorramasService {
//
//    private AhorramasProductHistoryRepository ahorramasProductHistoryRepository;
//
//    @Override
//    public ResponseEntity<AhorramasProductHistoryItem> saveProductHistory(AhorramasProductHistoryItem ahorramasProductHistory) {
//
//        log.debug("Buscando producto con el id {}", ahorramasProductHistory.getNombre());
//        Optional<AhorramasProductHistory> productHistoryOptional = ahorramasProductHistoryRepository.findById(ahorramasProductHistory.getNombre());
//
//        // Actualizar el historial del producto si existe
//        if (productHistoryOptional.isPresent()) {
//            AhorramasProductHistory productHistory = productHistoryOptional.get();
//            LocalDate latestDate = Collections.max(productHistory.getHistory().keySet());
//            double lastPrice = productHistory.getHistory().getOrDefault(latestDate, Double.MIN_VALUE);
//
//            // Si el precio más reciente es diferente al nuevo precio, se actualiza
//            log.debug("Comparando ultimo precio almacenado : {} con el del producto actual {}", lastPrice, ahorramasProductHistory.getPrice());
//            if (lastPrice != ahorramasProductHistory.getPrice()) {
//                log.debug("El actualizando precio en el histrial del producto: {}", ahorramasProductHistory);
//                productHistory.getHistory().put(ahorramasProductHistory.getFecha(), ahorramasProductHistory.getPrice());
//                ahorramasProductHistoryRepository.save(productHistory);
//                return ResponseEntity.status(HttpStatus.OK).body(ahorramasProductHistory);
//            } else {
//                // No hay cambios en el precio, no se realiza ninguna operación
//                log.debug("El precio del producto no ha sido modificado desde el último registro no se realizan cambios del producto: {}", ahorramasProductHistory);
//                return ResponseEntity.status(HttpStatus.NOT_MODIFIED).build();
//            }
//
//        } else {
//            // Crear un nuevo historial del producto si no existe
//            AhorramasProductHistory newProductHistory = AhorramasProductHistory.builder()
//                    .nombre(ahorramasProductHistory.getNombre())
//                    .history(Collections.singletonMap(ahorramasProductHistory.getFecha(), ahorramasProductHistory.getPrice()))
//                    .build();
//            ahorramasProductHistoryRepository.save(newProductHistory);
//            return ResponseEntity.status(HttpStatus.CREATED).body(ahorramasProductHistory);
//        }
//    }
//}