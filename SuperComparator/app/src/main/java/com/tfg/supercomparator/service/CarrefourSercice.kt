package com.tfg.supercomparator.service

import com.tfg.supercomparator.domain.modules.model.carrefour.product.CarrefourProduct
import com.tfg.supercomparator.domain.modules.model.carrefour.product.toProductHistoryItems
import com.tfg.supercomparator.domain.modules.model.product.ProductHistoryItems
import com.tfg.supercomparator.domain.modules.network.CarrefourQuoteService
import com.tfg.supercomparator.domain.modules.network.QuoteRepository
import retrofit2.Response

class CarrefourSercice : SearchProduct<CarrefourProduct> {
    override suspend fun findProducts(query: String): List<CarrefourProduct> {
        val service = QuoteRepository.getCarrefourProduct()
        val carrefourProductList = mutableListOf<CarrefourProduct>()

        val response = service.getCarrefourProduct(query)

        response.content.docs.forEach { product ->
            carrefourProductList.add(
                if (product.strikethroughPrice == 0.0) {
                    CarrefourProduct(
                        name = product.displayName,
                        price = product.activePrice,
                        pricePorUnidad = product.pricePerUnitText.replace(",", ".")
                            .split("€")[0].toDoubleOrNull()?: 0.0,
                        pricePorUnidadText = product.pricePerUnitText,
                        pricePorUnidadOfertaText = null,
                        pricePorUnidadOferta = null,
                        priceSinOfeta = null,
                        ofertaExtra = product.badge?.name,
                        imageUrl = product.imagePath,
                        productUrl = "https://www.carrefour.es${product.url}"
                    )
                } else {
                    CarrefourProduct(
                        name = product.displayName,
                        price = product.strikethroughPrice,
                        pricePorUnidad = product.pricePerUnitText.replace(",", ".")
                            .split("€")[0].toDoubleOrNull()?: 0.0,
                        pricePorUnidadText = product.pricePerUnitText,
                        pricePorUnidadOfertaText = product.appStrikethroughPricePerUnit,
                        pricePorUnidadOferta = product.appStrikethroughPricePerUnit.replace(
                            ",",
                            "."
                        ).split("€")[0].toDouble(),
                        priceSinOfeta = product.activePrice,
                        ofertaExtra = product.badge?.name,
                        imageUrl = product.imagePath,
                        productUrl = "https://www.carrefour.es${product.url}"
                    )
                }
            )
        }
        return carrefourProductList
    }

    override suspend fun saveProductHistory(product: List<CarrefourProduct>): Response<ProductHistoryItems> {
        return QuoteRepository
            .getSupercomparatorAPIClient()
            .create(CarrefourQuoteService::class.java)
            .saveHistory(product.toProductHistoryItems())
    }
}