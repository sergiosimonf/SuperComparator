package com.tfg.supercomparator.domain.modules.network

import com.tfg.supercomparator.domain.modules.model.hipercor.HipercorProduct
import retrofit2.http.GET
import retrofit2.http.Query


//http://localhost:8080/api/mercadona/?query=coca
interface HipercorQuoteService {
    @GET("hipercor/")
    suspend fun getHipercorProduct(
        @Query("query") query: String
    ): List<HipercorProduct>
}