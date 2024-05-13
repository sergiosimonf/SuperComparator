package com.tfg.supercomparator.infrastructure.controllers.hipercor;

import com.tfg.supercomparator.application.ports.in.hipercor.FindHipercorProductUseCase;
import com.tfg.supercomparator.application.ports.in.hipercor.HipercorSaveProductHistoryUseCase;
import com.tfg.supercomparator.domain.hipercor.HipercorProduct;
import com.tfg.supercomparator.domain.hipercor.HipercorProductHistory;
import com.tfg.supercomparator.domain.hipercor.HipercorProductHistoryItem;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RestController
@RequestMapping("api/hipercor")
@AllArgsConstructor
public class HipercorController {

    private HipercorSaveProductHistoryUseCase saveProductHistory;
    private FindHipercorProductUseCase findHipercorProductUseCase;

    @PostMapping("")
    public ResponseEntity<HipercorProductHistoryItem> saveProductHistoryDiaController(
            @RequestBody() HipercorProductHistoryItem product
    ) {
        return saveProductHistory.saveProductHistory(product);
    }

    @GetMapping("")
    public List<HipercorProduct> findProducts(
            @RequestParam(value = "query") String query
    ) {
        return findHipercorProductUseCase.findProduct(query);
    }
}