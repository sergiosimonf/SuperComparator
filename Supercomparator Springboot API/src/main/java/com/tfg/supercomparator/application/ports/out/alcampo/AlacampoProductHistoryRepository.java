package com.tfg.supercomparator.application.ports.out.alcampo;


import com.tfg.supercomparator.domain.alcampo.AlcampoProductHistory;
import org.springframework.data.repository.CrudRepository;

public interface AlacampoProductHistoryRepository extends CrudRepository<AlcampoProductHistory, String> {
}