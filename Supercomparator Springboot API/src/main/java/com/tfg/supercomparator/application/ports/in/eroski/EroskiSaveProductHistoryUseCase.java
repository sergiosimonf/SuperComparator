package com.tfg.supercomparator.application.ports.in.eroski;


import com.tfg.supercomparator.domain.eroski.EroskiProductHistoryItem;
import com.tfg.supercomparator.domain.eroski.EroskiProductHistoryItems;
import org.springframework.http.ResponseEntity;

public interface EroskiSaveProductHistoryUseCase {
    ResponseEntity<EroskiProductHistoryItem> saveProductHistory(EroskiProductHistoryItem eroskiProductHistory);

    ResponseEntity<EroskiProductHistoryItems> saveProductsHistory(EroskiProductHistoryItems eroskiProductHistory);
}
