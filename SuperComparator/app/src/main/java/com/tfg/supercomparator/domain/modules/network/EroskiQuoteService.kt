package com.tfg.supercomparator.domain.modules.network

import com.tfg.supercomparator.domain.modules.model.product.ProductHistory
import com.tfg.supercomparator.domain.modules.model.product.ProductHistoryItems
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Path

interface EroskiQuoteService {
    @POST("api/eroski/")
    suspend fun saveHistory(
        @Body prductHistoryItems: ProductHistoryItems
    ): Response<ProductHistoryItems>

    @GET("eroskiProductHistories/{product}")
    suspend fun getEroskiProductHistory(
        @Path("product") query: String,
    ): ProductHistory
}