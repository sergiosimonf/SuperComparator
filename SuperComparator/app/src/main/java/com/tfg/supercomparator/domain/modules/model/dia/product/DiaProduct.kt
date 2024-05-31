package com.tfg.supercomparator.domain.modules.model.dia.product

import com.tfg.supercomparator.R
import com.tfg.supercomparator.domain.modules.model.product.ItemProductHistory
import com.tfg.supercomparator.domain.modules.model.product.Product
import com.tfg.supercomparator.domain.modules.model.product.ProductHistoryItems
import java.time.LocalDate

data class DiaProduct(
    val name: String,
    val price: Double,
    val priceSinOferta: Double,
    val pricePerUnit: Double,
    val pricePerUnitText: String,
    val imageUrl: String,
    val productUrl: String,
    val hasOferta: Boolean = price.compareTo(priceSinOferta) != 0,
    val tiendaIcon: Int = R.drawable.dia,
)

fun DiaProduct.mapToProduct(): Product {
    return Product(
        name = this.name,
        price = this.price,
        priceOfert = this.price,
        priceNoOfert = this.priceSinOferta,
        ofertExtra = null,
        pricePerUnit = this.pricePerUnit,
        pricePerUnitOfert = null,
        pricePerUnitText = this.pricePerUnitText,
        pricePerUnitOfertText = null,
        imageUrl = this.imageUrl,
        productUrl = this.productUrl,
        hasOferta = this.hasOferta,
        hasOfertaExtra = false,
        tiendaIcon = tiendaIcon
    )
}

fun List<DiaProduct>.mapToProductList(): List<Product> {
    return this.map { it.mapToProduct() }
}

fun List<DiaProduct>.toProductHistoryItems(): ProductHistoryItems {
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