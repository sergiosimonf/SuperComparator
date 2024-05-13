package com.tfg.supercomparator.application.ports.in.hipercor;

import com.tfg.supercomparator.domain.hipercor.HipercorProduct;

import java.util.List;

public interface FindHipercorProductUseCase {
    List<HipercorProduct> findProduct(String product);
}
