package com.tfg.supercomparator.application.ports.out.mercadona;

import com.tfg.supercomparator.domain.mercadona.MercadonaProductHistory;
import org.springframework.data.repository.CrudRepository;

public interface MercadonaProductHistoryRepository extends CrudRepository<MercadonaProductHistory, String> {
}
