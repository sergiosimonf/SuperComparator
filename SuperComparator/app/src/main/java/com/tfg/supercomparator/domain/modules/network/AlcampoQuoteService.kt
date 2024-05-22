package com.tfg.supercomparator.domain.modules.network

import okhttp3.ResponseBody
import retrofit2.http.GET
import retrofit2.http.Query

interface AlcampoQuoteService {
    @GET("products/search?sort=price")
    suspend fun getAlcampoProduct(
        @Query("term") term: String,
    ): ResponseBody
}