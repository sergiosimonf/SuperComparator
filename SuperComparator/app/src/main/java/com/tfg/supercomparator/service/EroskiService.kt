package com.tfg.supercomparator.service

import com.tfg.supercomparator.domain.modules.model.eroski.EroskiProduct
import com.tfg.supercomparator.domain.modules.network.QuoteRepository

class EroskiService : SearchProduct<EroskiProduct> {
    override suspend fun findProducts(query: String): List<EroskiProduct> {
        val call = QuoteRepository.getEroskiProduct(query)
        val eroskiProductList = mutableListOf<EroskiProduct>()

        val elements = call.select(".product-item")

        elements.forEach { product ->
            val quantityPrice = product.select(".price-product").text()
            val priceQuantityText = product.select(".quantity-product").text()
            val productUrl = product.select(".product-title-resp a").attr("href")
            eroskiProductList.add(
                EroskiProduct(
                    name = product.select(".product-title-resp a").text(),
                    price = product.select(".price-offer-now").text().replace(",", ".").toDouble(),
                    priceWithoutOfert = product.select("span.price-offer-before").text().replace(",", ".").toDoubleOrNull(),
                    imageUrl = product.select(".product-img").attr("src"),
                    productUrl = "https://supermercado.eroski.es$productUrl",
                    pricePerQuantity = quantityPrice.replace(",", ".").replace("€", "").toDouble(),
                    pricePerQuantityText = "$quantityPrice/$priceQuantityText",
                )
            )
        }
        return eroskiProductList
    }
}