package com.tfg.supercomparator.application.ports.out.hipercor;


import com.tfg.supercomparator.domain.hipercor.HipercorProductHistory;
import org.springframework.data.repository.CrudRepository;

public interface HipercorProductHistoryRepository extends CrudRepository<HipercorProductHistory, String> {
}
