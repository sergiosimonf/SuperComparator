package com.tfg.supercomparator.service

import android.util.Log
import com.tfg.supercomparator.domain.modules.model.ahorramas.AhorramasProudct
import com.tfg.supercomparator.domain.modules.model.ahorramas.toProductHistoryItems
import com.tfg.supercomparator.domain.modules.model.product.ProductHistoryItems
import com.tfg.supercomparator.domain.modules.network.AhorramasQuoteService
import com.tfg.supercomparator.domain.modules.network.QuoteRepository
import retrofit2.Response

class AhorramasServices : SearchProduct<AhorramasProudct> {

    override suspend fun findProducts(query: String): List<AhorramasProudct> {
        val call = QuoteRepository.getAhorramasProduct(query)
        val products = mutableListOf<AhorramasProudct>()

        val elements = call.select(".product-container")

        elements.forEach { element ->
            val name = element.select(".product-name-gtm").text()
            val price = element.select(".value").attr("content").toDoubleOrNull()
            val priceNoOferta =
                element.select("span.value").last()?.attr("content")?.toDoubleOrNull()
            val pricePerQuantityNoFormated = element.select(".unit-price-per-unit").text()
            val imageUrl = element.select(".tile-image").attr("src")

            val (pricePerQuantityText, pricePerQuantityOfertaText) = pricePerQuantityFormatText(
                pricePerQuantityNoFormated
            )

            val (pricePerQuantity, pricePerQuantityOferta) = pricePerQuantityFormatDouble(
                pricePerQuantityText,
                pricePerQuantityOfertaText
            )

            val ahorramasProduct = createAhorramasProduct(
                name,
                price,
                priceNoOferta,
                pricePerQuantity ?: 0.0,
                pricePerQuantityText,
                pricePerQuantityOferta,
                pricePerQuantityOfertaText,
                imageUrl
            )


            ahorramasProduct?.let { products.add(it) }
        }
        return products
    }

    override suspend fun saveProductHistory(product: List<AhorramasProudct>): Response<ProductHistoryItems> {
        return QuoteRepository
            .getSupercomparatorAPIClient()
            .create(AhorramasQuoteService::class.java)
            .saveHistory(product.toProductHistoryItems())
    }

    private fun createAhorramasProduct(
        name: String,
        price: Double?,
        priceNoOferta: Double?,
        pricePerQuantity: Double,
        pricePerQuantityText: String,
        pricePerQuantityOferta: Double?,
        pricePerQuantityOfertaText: String,
        imageUrl: String,
    ): AhorramasProudct? {
        if (price != null) {
            val producto = if (priceNoOferta != null && price != priceNoOferta) {
                AhorramasProudct(
                    name,
                    priceNoOferta,
                    price,
                    pricePerQuantity,
                    pricePerQuantityText,
                    pricePerQuantityOferta,
                    pricePerQuantityOfertaText,
                    imageUrl
                )
            } else {
                AhorramasProudct(
                    name,
                    price,
                    null,
                    pricePerQuantity,
                    pricePerQuantityText,
                    null,
                    null,
                    imageUrl
                )
            }
            return producto
        }
        return null
    }

    private fun pricePerQuantityFormatText(pricePerQuantityNoFormated: String): Pair<String, String> {
        val pricePerQuantity: String
        var pricePerQuantityOferta = ""
        if (pricePerQuantityNoFormated.contains("|")) {
            val pricePerQuantityFormater =
                pricePerQuantityNoFormated.replace("(", "").replace(")", "").split("|")

            pricePerQuantity = pricePerQuantityFormater[0]
            pricePerQuantityOferta = pricePerQuantityFormater[1]
        } else {
            val pricePerQuantityFormater =
                pricePerQuantityNoFormated.replace("(", "").replace(")", "")

            pricePerQuantity = pricePerQuantityFormater
        }
        return Pair(pricePerQuantity, pricePerQuantityOferta)
    }

    private fun pricePerQuantityFormatDouble(
        pricePerQuantityText: String,
        pricePerQuantityOfertaText: String,
    ): Pair<Double?, Double?> {
        Log.d("Error", "$pricePerQuantityText ,$pricePerQuantityOfertaText ")
        val pricePerQuantity = pricePerQuantityText.replace(",", ".").split("€")[0].toDoubleOrNull()
        val pricePerQuantityOferta =
            pricePerQuantityOfertaText.replace(",", ".").split("€")[0].toDoubleOrNull()
        return Pair(pricePerQuantity, pricePerQuantityOferta)
    }
}