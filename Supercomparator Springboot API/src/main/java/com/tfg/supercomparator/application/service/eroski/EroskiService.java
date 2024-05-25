package com.tfg.supercomparator.application.service.eroski;

import com.tfg.supercomparator.domain.eroski.EroskiProductHistoryItem;
import com.tfg.supercomparator.domain.eroski.EroskiProductHistoryItems;
import org.springframework.http.ResponseEntity;

public interface EroskiService {
    ResponseEntity<EroskiProductHistoryItem> saveProductHistory(EroskiProductHistoryItem eroskiProductHistory);

    ResponseEntity<EroskiProductHistoryItems> saveProductsHistory(EroskiProductHistoryItems eroskiProductHistory);
}