package com.tfg.supercomparator.domain.modules.network

import com.tfg.supercomparator.domain.modules.model.carrefour.gson.Carrefour
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface CarrefourQuoteService {
    @GET("search?lang=es")
    suspend fun getCarrefourProduct(
        @Query("query") query: String
    ): Call<Carrefour>

}


//carrefourProduct.content.docs.get(0).display_name // Nombre del producto
//carrefourProduct.content.docs.get(0).brand // Nombre de la marca
//carrefourProduct.content.docs.get(0).app_price // precio con oferta
//carrefourProduct.content.docs.get(0).active_price // precio con oferta
//carrefourProduct.content.docs.get(0).app_strikethrough_price // precio sin oferta
//carrefourProduct.content.docs.get(0).price_per_unit_text // precio por unidad / unidad de medida
//carrefourProduct.content.docs.get(0).image_path // url de la imagen del prodcuto
//carrefourProduct.content.docs.get(0).measure_unit // unidad de medida
//carrefourProduct.content.docs.get(0).url // url del prodcuto url base https://www.carrefour.es