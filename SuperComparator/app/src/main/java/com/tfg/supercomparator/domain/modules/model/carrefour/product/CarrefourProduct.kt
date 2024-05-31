package com.tfg.supercomparator.domain.modules.model.carrefour.product

import com.tfg.supercomparator.R
import com.tfg.supercomparator.domain.modules.model.product.ItemProductHistory
import com.tfg.supercomparator.domain.modules.model.product.Product
import com.tfg.supercomparator.domain.modules.model.product.ProductHistoryItems
import java.time.LocalDate

data class CarrefourProduct(
    val name: String,
    val price: Double,
    val priceSinOfeta: Double?,
    val pricePorUnidad: Double,
    val pricePorUnidadText: String,
    val pricePorUnidadOferta: Double?,
    val pricePorUnidadOfertaText: String?,
    val imageUrl: String,
    val productUrl: String,
    val ofertaExtra: String?,
    val hasOferta: Boolean = priceSinOfeta != null,
    val hasOfertaExtra: Boolean = ofertaExtra != null,
    val tiendaIcon: Int = R.drawable.carrefour,
)

fun CarrefourProduct.mapToProduct(): Product {
    return Product(
        name = this.name,
        price = this.price,
        priceOfert = this.priceSinOfeta,
        priceNoOfert = this.price,
        ofertExtra = ofertaExtra,
        pricePerUnit = this.pricePorUnidad,
        pricePerUnitOfert = this.pricePorUnidadOferta,
        pricePerUnitText = this.pricePorUnidadText,
        pricePerUnitOfertText = this.pricePorUnidadOfertaText,
        imageUrl = this.imageUrl,
        productUrl = this.productUrl,
        hasOferta = this.hasOferta,
        hasOfertaExtra = this.hasOfertaExtra,
        tiendaIcon = tiendaIcon
    )
}

fun List<CarrefourProduct>.mapToProductList(): List<Product> {
    return this.map { it.mapToProduct() }
}

fun List<CarrefourProduct>.toProductHistoryItems(): ProductHistoryItems {
    val items = this.map { product ->
        ItemProductHistory(
            nombre = product.name,
            price = product.price.toFloat()
        )
    }
    return ProductHistoryItems(
        fecha = LocalDate.now().toString(),
        products = items
    )
}