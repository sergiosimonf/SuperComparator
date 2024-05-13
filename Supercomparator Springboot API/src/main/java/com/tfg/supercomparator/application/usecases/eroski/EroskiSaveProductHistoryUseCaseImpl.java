package com.tfg.supercomparator.application.usecases.eroski;


import com.tfg.supercomparator.application.ports.in.eroski.EroskiSaveProductHistoryUseCase;
import com.tfg.supercomparator.application.service.eroski.EroskiService;
import com.tfg.supercomparator.domain.eroski.EroskiProductHistory;
import com.tfg.supercomparator.domain.eroski.EroskiProductHistoryItem;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

@Component
@AllArgsConstructor
public class EroskiSaveProductHistoryUseCaseImpl implements EroskiSaveProductHistoryUseCase {

    private EroskiService eroskiService;

    @Override
    public ResponseEntity<EroskiProductHistoryItem> saveProductHistory(EroskiProductHistoryItem eroskiProductHistory) {
        return eroskiService.saveProductHistory(eroskiProductHistory);
    }
}