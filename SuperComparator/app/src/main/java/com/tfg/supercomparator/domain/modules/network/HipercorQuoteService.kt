package com.tfg.supercomparator.domain.modules.network

import com.tfg.supercomparator.domain.modules.model.hipercor.HipercorProduct
import retrofit2.http.GET
import retrofit2.http.Query


interface HipercorQuoteService {
    @GET("api/hipercor/")
    suspend fun getHipercorProduct(
        @Query("query") query: String,
    ): List<HipercorProduct>
}