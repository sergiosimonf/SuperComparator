package com.tfg.supercomparator.domain.modules.network

import com.tfg.supercomparator.domain.modules.model.mercadona.MercadonaProduct
import retrofit2.http.GET
import retrofit2.http.Query

interface MercadonaQuoteService {
    @GET("mercadona/")
    suspend fun getMercadonaProduct(
        @Query("query") query: String
    ): List<MercadonaProduct>
}