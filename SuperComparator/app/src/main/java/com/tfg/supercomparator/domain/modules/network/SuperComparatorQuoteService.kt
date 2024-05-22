package com.tfg.supercomparator.domain.modules.network

import com.tfg.supercomparator.domain.modules.model.api.Message
import retrofit2.http.GET

interface SuperComparatorQuoteService {
    @GET("api/")
    suspend fun testConexion(): Message
}