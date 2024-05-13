package com.tfg.supercomparator.application.service.eroski;

import com.tfg.supercomparator.domain.eroski.EroskiProductHistory;
import com.tfg.supercomparator.domain.eroski.EroskiProductHistoryItem;
import org.springframework.http.ResponseEntity;

public interface EroskiService {
    ResponseEntity<EroskiProductHistoryItem> saveProductHistory(EroskiProductHistoryItem eroskiProductHistory);
}