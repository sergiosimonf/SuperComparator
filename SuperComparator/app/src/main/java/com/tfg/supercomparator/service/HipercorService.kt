package com.tfg.supercomparator.service

import com.tfg.supercomparator.domain.modules.model.hipercor.HipercorProduct
import com.tfg.supercomparator.domain.modules.model.hipercor.toProductHistoryItems
import com.tfg.supercomparator.domain.modules.model.product.ProductHistoryItems
import com.tfg.supercomparator.domain.modules.network.HipercorQuoteService
import com.tfg.supercomparator.domain.modules.network.QuoteRepository
import retrofit2.Response

class HipercorService : SearchProduct<HipercorProduct> {
    override suspend fun findProducts(query: String): List<HipercorProduct> {
        return QuoteRepository
            .getSupercomparatorAPIClient()
            .create(HipercorQuoteService::class.java)
            .getHipercorProduct(query)
    }

    override suspend fun saveProductHistory(product: List<HipercorProduct>): Response<ProductHistoryItems> {
        return QuoteRepository
            .getSupercomparatorAPIClient()
            .create(HipercorQuoteService::class.java)
            .saveHistory(product.toProductHistoryItems())
    }
}