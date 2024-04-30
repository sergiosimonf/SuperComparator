package com.tfg.supercomparator.domain.modules.network

import com.tfg.supercomparator.domain.modules.model.dia.gson.Dia
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface DiaQuoteService {
    @GET("search-back/search/reduced?")
    suspend fun getDiaProduct(
        @Query("q") q: String
    ): Call<Dia>
}

//"prices": {
//    "currency": "EUR",
//    "discount_percentage": 0,
//    "is_club_price": false,
//    "is_promo_price": false,
//    "measure_unit": "LITRO", /////////////////////////////
//    "price": 3.72,
//    "price_per_unit": 0.94, /////////////////////////////////////////////
//    "strikethrough_price": 3.72 // Precio sin oferta
//},