package com.tfg.supercomparator.domain.modules.network

import com.tfg.supercomparator.domain.modules.model.dia.gson.Dia
import com.tfg.supercomparator.domain.modules.model.product.ProductHistoryItems
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Query

interface DiaQuoteService {
    @GET("search-back/search/reduced?")
    suspend fun getDiaProduct(
        @Query("q") q: String,
    ): Dia

    @POST("api/dia/")
    suspend fun saveHistory(
        @Body prductHistoryItems: ProductHistoryItems
    ): Response<ProductHistoryItems>
}