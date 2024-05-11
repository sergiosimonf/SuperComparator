package com.tfg.supercomparator.domain.modules.model.mercadona

import com.tfg.supercomparator.domain.modules.model.product.Product

data class MercadonaProduct(
    val name: String,
    val price: Double,
    val pricePerQuantity: Double,
    val pricePerQuantityText: String,
    val priceDiscount: Double?,
    val priceNoDiscount: Double?,
    val imageUrl: String,
    val isDiscount: Boolean = priceDiscount != null
)

private fun MercadonaProduct.mapToProduct(): Product {
    return Product(
        name = this.name,
        price = this.price,
        priceOfert = this.priceDiscount,
        priceNoOfert = this.priceNoDiscount,
        ofertExtra = null,
        pricePerUnit = this.pricePerQuantity,
        pricePerUnitOfert = null,
        pricePerUnitText = this.pricePerQuantityText,
        pricePerUnitOfertText = null,
        imageUrl = this.imageUrl,
        productUrl = null,
        hasOferta = this.isDiscount,
        hasOfertaExtra = false
    )
}

fun List<MercadonaProduct>.mapToProductList(): List<Product> {
    return this.map { it.mapToProduct() }
}

