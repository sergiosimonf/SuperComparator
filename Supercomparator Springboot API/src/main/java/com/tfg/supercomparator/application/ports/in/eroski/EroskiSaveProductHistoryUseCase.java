package com.tfg.supercomparator.application.ports.in.eroski;


import com.tfg.supercomparator.domain.eroski.EroskiProductHistory;
import com.tfg.supercomparator.domain.eroski.EroskiProductHistoryItem;
import org.springframework.http.ResponseEntity;

public interface EroskiSaveProductHistoryUseCase {
    ResponseEntity<EroskiProductHistoryItem> saveProductHistory(EroskiProductHistoryItem eroskiProductHistory);
}
