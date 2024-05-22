package com.tfg.supercomparator.domain.modules.network

import com.tfg.supercomparator.domain.modules.model.mercadona.MercadonaProduct
import retrofit2.http.GET
import retrofit2.http.Query

//http://localhost:8080/api/mercadona/?query=coca
interface MercadonaQuoteService {
    @GET("api/mercadona/")
    suspend fun getMercadonaProduct(
        @Query("query") query: String,
    ): List<MercadonaProduct>
}