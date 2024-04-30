package com.tfg.supercomparator.domain.modules.model.eroski

import com.tfg.supercomparator.domain.modules.model.product.Product

data class Eroski(
    val name: String,
    val price: Double,
    val pricePerQuantity: Double,
    val pricePerQuantityText: String,
    val oferta: String,
    val hasOferta: Boolean = oferta.isNotEmpty(),
    val productUrl: String,
    val imageUrl: String
) {
    fun Eroski.mapToProduct(): Product {
        return Product(
            name = this.name,
            price = this.price,
            priceOfert = null,
            priceNoOfert = null,
            ofertExtra = this.oferta,
            pricePerUnit = this.pricePerQuantity,
            pricePerUnitOfert = null,
            pricePerUnitText = this.pricePerQuantityText,
            pricePerUnitOfertText = null,
            imageUrl = this.imageUrl,
            productUrl = this.productUrl,
            hasOferta = this.hasOferta,
            hasOfertaExtra = false
        )
    }

    fun List<Eroski>.mapToProductList(): List<Product> {
        return this.map { it.mapToProduct() }
    }
}