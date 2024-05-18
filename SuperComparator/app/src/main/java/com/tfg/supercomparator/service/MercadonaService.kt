package com.tfg.supercomparator.service

import com.tfg.supercomparator.domain.modules.model.mercadona.MercadonaProduct
import com.tfg.supercomparator.domain.modules.network.QuoteRepository

class MercadonaService : SearchProduct<MercadonaProduct> {
    override suspend fun findProducts(query: String): List<MercadonaProduct> {
        val service = QuoteRepository.getMercadonaProduct(query)
        return service.getMercadonaProduct(query)
    }
}