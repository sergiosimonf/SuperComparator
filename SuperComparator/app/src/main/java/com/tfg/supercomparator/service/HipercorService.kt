package com.tfg.supercomparator.service

import com.tfg.supercomparator.domain.modules.model.hipercor.HipercorProduct
import com.tfg.supercomparator.domain.modules.network.HipercorQuoteService
import com.tfg.supercomparator.domain.modules.network.QuoteRepository

class HipercorService : SearchProduct<HipercorProduct> {
    override suspend fun findProducts(query: String): List<HipercorProduct> {
        return QuoteRepository
            .getSupercomparatorAPIClient()
            .create(HipercorQuoteService::class.java)
            .getHipercorProduct(query)
    }
}