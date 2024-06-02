package com.tfg.supercomparator.domain.modules.network

import com.tfg.supercomparator.domain.modules.model.mercadona.MercadonaProduct
import com.tfg.supercomparator.domain.modules.model.product.ProductHistory
import com.tfg.supercomparator.domain.modules.model.product.ProductHistoryItems
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Path
import retrofit2.http.Query

//http://localhost:8080/api/mercadona/?query=coca
interface MercadonaQuoteService {
    @GET("api/mercadona/")
    suspend fun getMercadonaProduct(
        @Query("query") query: String,
    ): List<MercadonaProduct>

    @POST("api/mercadona/")
    suspend fun saveHistory(
        @Body prductHistoryItems: ProductHistoryItems
    ): Response<ProductHistoryItems>

    @GET("mercadonaProductHistories/{product}")
    suspend fun getMercadonaProductHistory(
        @Path("product") query: String,
    ): ProductHistory
}