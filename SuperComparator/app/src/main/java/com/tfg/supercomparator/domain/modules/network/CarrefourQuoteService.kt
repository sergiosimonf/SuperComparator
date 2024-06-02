package com.tfg.supercomparator.domain.modules.network

import com.tfg.supercomparator.domain.modules.model.carrefour.gson.Carrefour
import com.tfg.supercomparator.domain.modules.model.product.ProductHistory
import com.tfg.supercomparator.domain.modules.model.product.ProductHistoryItems
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Path
import retrofit2.http.Query

interface CarrefourQuoteService {
    @GET("search?lang=es")
    suspend fun getCarrefourProduct(
        @Query("query") query: String,
    ): Carrefour

    @POST("api/carrefour/")
    suspend fun saveHistory(
        @Body prductHistoryItems: ProductHistoryItems
    ): Response<ProductHistoryItems>

    @GET("carrefourProductHistories/{product}")
    suspend fun getCarrefourProductHistory(
        @Path("product") query: String,
    ): ProductHistory
}