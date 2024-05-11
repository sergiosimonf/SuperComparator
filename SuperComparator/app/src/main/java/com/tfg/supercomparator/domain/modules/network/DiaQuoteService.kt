package com.tfg.supercomparator.domain.modules.network

import com.tfg.supercomparator.domain.modules.model.dia.gson.Dia
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface DiaQuoteService {
    @GET("search-back/search/reduced?")
    suspend fun getDiaProduct(
        @Query("q") q: String
    ): Dia
}