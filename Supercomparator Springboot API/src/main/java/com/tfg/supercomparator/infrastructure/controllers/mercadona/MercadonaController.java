package com.tfg.supercomparator.infrastructure.controllers.mercadona;

import com.tfg.supercomparator.application.ports.in.mercadona.FindMercadonaProductUseCase;
import com.tfg.supercomparator.application.ports.in.mercadona.MercadonaSaveProductHistoryUseCase;
import com.tfg.supercomparator.domain.mercadona.MercadonaProduct;
import com.tfg.supercomparator.domain.mercadona.MercadonaProductHistoryItem;
import com.tfg.supercomparator.domain.utils.Fecha;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RestController
@RequestMapping("/api/mercadona/")
@AllArgsConstructor
public class MercadonaController {

    private MercadonaSaveProductHistoryUseCase saveProductHistory;
    private FindMercadonaProductUseCase mercadonaProductUseCase;


    @PostMapping("")
    public ResponseEntity<MercadonaProductHistoryItem> saveProductHistoryDiaController(
            @RequestBody() MercadonaProductHistoryItem product
    ) {
       return saveProductHistory.saveProductHistory(product);
    }

    @GetMapping("")
    public List<MercadonaProduct> findProducts(
            @RequestParam(value = "query") String query
    ) {
        return mercadonaProductUseCase.findProduct(query);
    }
}