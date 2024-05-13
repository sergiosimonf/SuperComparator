package com.tfg.supercomparator.application.ports.out.carrefour;


import com.tfg.supercomparator.domain.carrefour.CarrefourProductHistory;
import org.springframework.data.repository.CrudRepository;

public interface CarrefourProductHistoryRepository extends CrudRepository<CarrefourProductHistory, String> {
}
