package com.tfg.supercomparator.service

import android.util.Log
import com.tfg.supercomparator.domain.modules.model.dia.product.DiaProduct
import com.tfg.supercomparator.domain.modules.network.QuoteRepository
import org.jsoup.Jsoup

class DiaService : SearchProduct<DiaProduct> {
    override suspend fun findProducts(query: String): List<DiaProduct> {
        val service = QuoteRepository.getDiaProduct()
        val diaProductList = mutableListOf<DiaProduct>()

        val response = service.getDiaProduct(query)
        Log.e("Response Dia", response.toString())

        response.search_items.forEach { products ->
            diaProductList.add(
                DiaProduct(
                    name = products.display_name,
                    price = products.prices.price,
                    pricePerUnit = products.prices.price_per_unit,
                    pricePerUnitText = "${products.prices.price_per_unit} €/${products.prices.measure_unit.lowercase()}",
                    priceSinOferta = products.prices.strikethrough_price,
                    imageUrl = "https://www.dia.es${products.image}",
                    productUrl = "https://www.dia.es${products.url}",
                )
            )
        }

        return diaProductList
    }
}