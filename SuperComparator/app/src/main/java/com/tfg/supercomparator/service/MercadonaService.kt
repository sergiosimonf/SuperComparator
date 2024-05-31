package com.tfg.supercomparator.service

import com.tfg.supercomparator.domain.modules.model.mercadona.MercadonaProduct
import com.tfg.supercomparator.domain.modules.model.mercadona.toProductHistoryItems
import com.tfg.supercomparator.domain.modules.model.product.ProductHistoryItems
import com.tfg.supercomparator.domain.modules.network.MercadonaQuoteService
import com.tfg.supercomparator.domain.modules.network.QuoteRepository
import retrofit2.Response

class MercadonaService : SearchProduct<MercadonaProduct> {
    override suspend fun findProducts(query: String): List<MercadonaProduct> {
        return QuoteRepository
            .getSupercomparatorAPIClient()
            .create(MercadonaQuoteService::class.java)
            .getMercadonaProduct(query)
    }

    override suspend fun saveProductHistory(product: List<MercadonaProduct>): Response<ProductHistoryItems> {
        return QuoteRepository
            .getSupercomparatorAPIClient()
            .create(MercadonaQuoteService::class.java)
            .saveHistory(product.toProductHistoryItems())
    }
}