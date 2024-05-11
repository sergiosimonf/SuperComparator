package com.tfg.supercomparator.service

import com.google.gson.Gson
import com.google.gson.JsonObject
import com.tfg.supercomparator.domain.modules.model.alcampo.gson.Alcampo
import com.tfg.supercomparator.domain.modules.model.alcampo.product.AlcampoProduct
import com.tfg.supercomparator.domain.modules.network.QuoteRepository

class AlcampoService : SearchProduct<AlcampoProduct> {
    override suspend fun findProducts(query: String): List<AlcampoProduct> {
        val service = QuoteRepository.getAlcampoProduct()
        val alcampoProductList = mutableListOf<AlcampoProduct>()

        val response = service.getAlcampoProduct(query)

        val jsonObject: JsonObject =
            Gson().fromJson(response.string(), JsonObject::class.java)

        val productsObject = jsonObject
            .getAsJsonObject("entities")
            .getAsJsonObject("product")

        productsObject.entrySet().forEach { product ->
            val alcampoProduct =
                Gson().fromJson(product.value.toString(), Alcampo::class.java)

            alcampoProductList.add(
                AlcampoProduct(
                    name = alcampoProduct.name,
                    price = alcampoProduct.price.current.amount.toDouble(),
                    pricePerUnit = alcampoProduct.price.unit.current.amount.toDouble(),
                    pricePerUnitText = "${alcampoProduct.price.unit.current.amount}${
                        formatPriceUnit(
                            alcampoProduct.price.unit.label
                        )
                    }",
                    imageUrl = alcampoProduct.image.src
                )
            )
        }

        return alcampoProductList
    }

    private fun formatPriceUnit(unit: String): String {
        return when (unit) {
            "fop.price.per.litre" -> "€/Litro"
            "fop.price.per.kg" -> "€/Kg"
            else -> unit
        }
    }
}