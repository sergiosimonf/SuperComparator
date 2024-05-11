package com.tfg.supercomparator.domain.modules.model.alcampo.product

import com.tfg.supercomparator.domain.modules.model.product.Product

data class AlcampoProduct(
    val name: String,
    val price: Double,
    val pricePerUnit: Double,
    val pricePerUnitText: String,
    val imageUrl: String,
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
        hasOfertaExtra = false
    )
}

fun List<AlcampoProduct>.mapToProductList(): List<Product> {
    return this.map { it.mapToProduct() }
}
