package com.tfg.supercomparator.domain.modules.network

import org.jsoup.Jsoup
import org.jsoup.nodes.Document
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object QuoteRepository {

    var apiConexion = false

    fun getDiaProduct(): DiaQuoteService {
        return Retrofit.Builder()
            .baseUrl("https://www.dia.es/api/v1/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(DiaQuoteService::class.java)
    }

    fun getAlcampoProduct(): AlcampoQuoteService {
        return Retrofit.Builder()
            .baseUrl("https://www.compraonline.alcampo.es/api/v5/")
            .build()
            .create(AlcampoQuoteService::class.java)
    }

    fun getCarrefourProduct(): CarrefourQuoteService {
        return Retrofit.Builder()
            .baseUrl("https://www.carrefour.es/search-api/query/v1/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(CarrefourQuoteService::class.java)
    }

    fun getAhorramasProduct(query: String): Document {
        val url = "https://www.ahorramas.com/buscador?q=$query"
        return Jsoup.connect(url).get()
    }

    fun getEroskiProduct(query: String): Document {
        val url = "https://supermercado.eroski.es/es/search/results/?q=$query"
        return Jsoup.connect(url).get()
    }

    // 172.26.160.1
    // 192.168.1.113
    fun getSupercomparatorAPIClient(): Retrofit {
        return Retrofit.Builder()
            .baseUrl("http://192.168.1.113:8080/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }
}