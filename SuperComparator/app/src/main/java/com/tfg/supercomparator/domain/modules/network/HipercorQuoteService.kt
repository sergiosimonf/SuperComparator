package com.tfg.supercomparator.domain.modules.network

import com.tfg.supercomparator.domain.modules.model.hipercor.HipercorProduct
import com.tfg.supercomparator.domain.modules.model.product.ProductHistory
import com.tfg.supercomparator.domain.modules.model.product.ProductHistoryItems
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Path
import retrofit2.http.Query


interface HipercorQuoteService {
    @GET("api/hipercor/")
    suspend fun getHipercorProduct(
        @Query("query") query: String,
    ): List<HipercorProduct>

    @POST("api/hipercor/")
    suspend fun saveHistory(
        @Body prductHistoryItems: ProductHistoryItems
    ): Response<ProductHistoryItems>

    @GET("hipercorProductHistories/{product}")
    suspend fun getHipercorProductHistory(
        @Path("product") query: String,
    ): ProductHistory
}