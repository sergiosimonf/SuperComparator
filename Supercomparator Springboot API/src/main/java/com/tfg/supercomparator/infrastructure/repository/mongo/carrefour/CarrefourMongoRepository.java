package com.tfg.supercomparator.infrastructure.repository.mongo.carrefour;

import com.tfg.supercomparator.application.ports.out.carrefour.CarrefourProductHistoryRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CarrefourMongoRepository extends CarrefourProductHistoryRepository {
}