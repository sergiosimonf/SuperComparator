package com.tfg.supercomparator.application.ports.out.dia;


import com.tfg.supercomparator.domain.dia.DiaProductHistory;
import org.springframework.data.repository.CrudRepository;

public interface DiaProductHistoryRepository extends CrudRepository<DiaProductHistory, String> {
}
