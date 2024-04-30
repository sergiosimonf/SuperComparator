package com.tfg.supercomparator.domain.modules.network

import com.tfg.supercomparator.domain.modules.model.carrefour.gson.Carrefour
import com.tfg.supercomparator.domain.modules.model.dia.gson.Dia
import okhttp3.ResponseBody
import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class QuoteRepository {
    suspend fun getDiaProduct(query: String): Call<Dia> {
        val diaQuoteService = Retrofit.Builder()
            .baseUrl("https://www.dia.es/api/v1/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(DiaQuoteService::class.java)

        return diaQuoteService.getDiaProduct(query)
    }

    suspend fun getAlcampoProduct(query: String): Call<ResponseBody> {
        val alcampoQuoteService = Retrofit.Builder()
            .baseUrl("https://www.compraonline.alcampo.es/api/v5/")
            .build()
            .create(AlcampoQuoteService::class.java)

        return alcampoQuoteService.getAlcampoProduct(query)
    }

    suspend fun getCarrefourProduct(query: String): Call<Carrefour> {
        val carrefourProduct = Retrofit.Builder()
            .baseUrl("https://www.carrefour.es/search-api/query/v1/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(CarrefourQuoteService::class.java)

        return carrefourProduct.getCarrefourProduct(query)
    }
}