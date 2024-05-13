package com.tfg.supercomparator.application.ports.out.ahorramas;


import com.tfg.supercomparator.domain.ahorramas.AhorramasProductHistory;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AhorramasProductHistoryRepository extends CrudRepository<AhorramasProductHistory, String> {
}