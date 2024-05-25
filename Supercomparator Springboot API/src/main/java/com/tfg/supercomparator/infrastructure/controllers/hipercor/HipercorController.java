package com.tfg.supercomparator.infrastructure.controllers.hipercor;

import com.tfg.supercomparator.application.ports.in.hipercor.FindHipercorProductUseCase;
import com.tfg.supercomparator.application.ports.in.hipercor.HipercorSaveProductHistoryUseCase;
import com.tfg.supercomparator.domain.hipercor.HipercorProduct;
import com.tfg.supercomparator.domain.hipercor.HipercorProductHistoryItem;
import com.tfg.supercomparator.domain.hipercor.HipercorProductHistoryItems;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RestController
@RequestMapping("api/hipercor/")
@AllArgsConstructor
public class HipercorController {

    private HipercorSaveProductHistoryUseCase saveProductHistory;
    private FindHipercorProductUseCase findHipercorProductUseCase;

    @GetMapping("")
    public List<HipercorProduct> findProducts(
            @RequestParam(value = "query") String query
    ) {
        return findHipercorProductUseCase.findProduct(query);
    }

    @PostMapping("item")
    public ResponseEntity<HipercorProductHistoryItem> saveProductsHistoryHipercorController(
            @RequestBody() HipercorProductHistoryItem product
    ) {
        return saveProductHistory.saveProductHistory(product);
    }

    @PostMapping("")
    public ResponseEntity<HipercorProductHistoryItems> saveProductsHistoryHipercorController(
            @RequestBody() HipercorProductHistoryItems products
    ) {
        return saveProductHistory.saveProductsHistory(products);
    }
}