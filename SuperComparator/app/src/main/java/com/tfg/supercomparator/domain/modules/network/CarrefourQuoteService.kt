package com.tfg.supercomparator.domain.modules.network

import com.tfg.supercomparator.domain.modules.model.carrefour.gson.Carrefour
import retrofit2.http.GET
import retrofit2.http.Query

interface CarrefourQuoteService {
    @GET("search?lang=es")
    suspend fun getCarrefourProduct(
        @Query("query") query: String,
    ): Carrefour
}