package com.tfg.supercomparator.application.usecases.hipercor;


import com.tfg.supercomparator.application.ports.in.hipercor.FindHipercorProductUseCase;
import com.tfg.supercomparator.application.service.hipercor.HipercorService;
import com.tfg.supercomparator.domain.hipercor.HipercorProduct;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@AllArgsConstructor
public class FindHipercorProductUseCaseImpl implements FindHipercorProductUseCase {

    private HipercorService hipercorService;

    @Override
    public List<HipercorProduct> findProduct(String product) {
        return hipercorService.findProduct(product);
    }
}