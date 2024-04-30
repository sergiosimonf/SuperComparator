package com.tfg.supercomparator.domain.modules.network

import com.google.gson.Gson
import com.google.gson.JsonParser
import okhttp3.ResponseBody
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface AlcampoQuoteService {
    @GET("products/search?sort=price")
    suspend fun getAlcampoProduct(
        @Query("term") term: String
    ): Call<ResponseBody>
}


//fun obtenerProductos() {
//    val productService = obtenerRetrofit().create(AlcampoQuoteService::class.java)
//    val call = productService.getAlcampoProduct("azucar")
//
//    call.enqueue(object : Callback<ResponseBody> {
//        override fun onResponse(call: Call<ResponseBody>, response: Response<ResponseBody>) {
//            if (response.isSuccessful) {
//                val json = response.body()?.string()
//
//                val gson = Gson()
//                val jsonObject = JsonParser.parseString(json).asJsonObject
//                val entities = jsonObject.getAsJsonObject("entities")
//                val productsObject = entities.getAsJsonObject("product")
//
//                val alcampoProductList = mutableListOf<Alcampo>()
//                productsObject.entrySet().forEach { product ->
//
//                    val alcampoProduct = gson.fromJson(product.value.toString(), Alcampo::class.java)
//
//                    alcampoProductList.add(alcampoProduct)
//                }
//
//                alcampoProductList.forEach { product ->
//                    println("Product: $product")
//                }
//            } else {
//                println("Error en la solicitud: ${response.code()}")
//            }
//        }
//
//        override fun onFailure(call: Call<ResponseBody>, t: Throwable) {
//            println("Error al realizar la solicitud: ${t.message}")
//        }
//    })
//}