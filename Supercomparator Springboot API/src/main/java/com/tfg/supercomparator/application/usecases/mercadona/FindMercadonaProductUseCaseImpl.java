package com.tfg.supercomparator.application.usecases.mercadona;


import com.tfg.supercomparator.application.ports.in.mercadona.FindMercadonaProductUseCase;
import com.tfg.supercomparator.application.service.mercadona.MercadonaService;
import com.tfg.supercomparator.domain.mercadona.MercadonaProduct;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@AllArgsConstructor
public class FindMercadonaProductUseCaseImpl implements FindMercadonaProductUseCase {

    private MercadonaService mercadonaService;

    @Override
    public List<MercadonaProduct> findProduct(String product) {
        return mercadonaService.findProduct(product);
    }
}