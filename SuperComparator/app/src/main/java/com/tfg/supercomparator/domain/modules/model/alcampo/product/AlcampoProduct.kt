package com.tfg.supercomparator.domain.modules.model.alcampo.product

import com.tfg.supercomparator.R
import com.tfg.supercomparator.domain.modules.model.product.ItemProductHistory
import com.tfg.supercomparator.domain.modules.model.product.Product
import com.tfg.supercomparator.domain.modules.model.product.ProductHistoryItems
import java.time.LocalDate

data class AlcampoProduct(
    val name: String,
    val price: Double,
    val pricePerUnit: Double,
    val pricePerUnitText: String,
    val imageUrl: String,
    val tiendaIcon: Int = R.drawable.alcampo,
)

fun AlcampoProduct.mapToProduct(): Product {
    return Product(
        name = this.name,
        price = this.price,
        priceOfert = null,
        priceNoOfert = this.price,
        ofertExtra = null,
        pricePerUnit = this.pricePerUnit,
        pricePerUnitOfert = null,
        pricePerUnitText = pricePerUnitText,
        pricePerUnitOfertText = null,
        imageUrl = this.imageUrl,
        productUrl = null,
        hasOferta = false,
        hasOfertaExtra = false,
        tiendaIcon = this.tiendaIcon
    )
}

fun List<AlcampoProduct>.mapToProductList(): List<Product> {
    return this.map { it.mapToProduct() }
}

fun List<AlcampoProduct>.toProductHistoryItems(): ProductHistoryItems {
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