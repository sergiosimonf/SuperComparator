package com.tfg.supercomparator.domain.modules.model.ahorramas

import com.tfg.supercomparator.domain.modules.model.product.Product

data class AhorramasProudct(
    val name: String,
    val price: Double,
    val priceOfert: Double?,
    val pricePerQuantity: Double,
    val pricePerQuantityText: String,
    val pricePerQuantityOferta: Double?,
    val pricePerQuantityOfertaText: String?,
    val imageUrl: String,
    val tieneOferta: Boolean = priceOfert != null
)

fun AhorramasProudct.mapToProduct(): Product {
    return Product(
        name = this.name,
        price = this.price,
        priceOfert = this.priceOfert,
        priceNoOfert = this.price,
        ofertExtra = null,
        pricePerUnit = this.pricePerQuantity,
        pricePerUnitOfert = this.pricePerQuantityOferta,
        pricePerUnitText = this.pricePerQuantityText,
        pricePerUnitOfertText = this.pricePerQuantityOfertaText,
        imageUrl = this.imageUrl,
        productUrl = null,
        hasOferta = this.tieneOferta,
        hasOfertaExtra = false
    )
}

fun List<AhorramasProudct>.mapToProductList(): List<Product> {
    return this.map { it.mapToProduct() }
}
