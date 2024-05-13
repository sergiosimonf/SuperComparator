package com.tfg.supercomparator.application.ports.out.mercadona;


import com.tfg.supercomparator.domain.mercadona.MercadonaProduct;
import org.springframework.data.repository.CrudRepository;

public interface MercadonaRepository extends CrudRepository<MercadonaProduct, String> {
}
