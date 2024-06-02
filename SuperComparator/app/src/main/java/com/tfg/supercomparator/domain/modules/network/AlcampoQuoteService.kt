package com.tfg.supercomparator.domain.modules.network

import com.tfg.supercomparator.domain.modules.model.product.ProductHistory
import com.tfg.supercomparator.domain.modules.model.product.ProductHistoryItems
import okhttp3.ResponseBody
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Path
import retrofit2.http.Query

interface AlcampoQuoteService {
    @GET("products/search?sort=price")
    suspend fun getAlcampoProduct(
        @Query("term") term: String,
    ): ResponseBody

    @POST("api/alcampo/")
    suspend fun saveHistory(
        @Body prductHistoryItems: ProductHistoryItems
    ): Response<ProductHistoryItems>

    @GET("alcampoProductHistories/{product}")
    suspend fun getAlcampoProductHistory(
        @Path("product") query: String,
    ): ProductHistory
}