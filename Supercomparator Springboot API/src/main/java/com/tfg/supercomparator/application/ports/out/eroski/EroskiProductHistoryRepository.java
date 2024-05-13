package com.tfg.supercomparator.application.ports.out.eroski;


import com.tfg.supercomparator.domain.eroski.EroskiProductHistory;
import org.springframework.data.repository.CrudRepository;

public interface EroskiProductHistoryRepository extends CrudRepository<EroskiProductHistory, String> {
}
