package com.tfg.supercomparator.domain.modules.network

import com.tfg.supercomparator.domain.modules.model.product.ProductHistoryItems
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.POST

interface AhorramasQuoteService {
    @POST("api/ahorramas/")
    suspend fun saveHistory(
        @Body prductHistoryItems: ProductHistoryItems
    ): Response<ProductHistoryItems>
}