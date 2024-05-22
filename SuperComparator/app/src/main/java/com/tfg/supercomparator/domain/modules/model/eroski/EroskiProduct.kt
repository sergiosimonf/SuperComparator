package com.tfg.supercomparator.domain.modules.model.eroski

import com.tfg.supercomparator.R
import com.tfg.supercomparator.domain.modules.model.product.Product

data class EroskiProduct(
    val name: String,
    val price: Double,
    val priceWithoutOfert: Double?,
    val pricePerQuantity: Double,
    val pricePerQuantityText: String,
    val hasOferta: Boolean = priceWithoutOfert != null,
    val productUrl: String?,
    val imageUrl: String,
    val tiendaIcon: Int = R.drawable.eroski,
)

fun EroskiProduct.mapToProduct(): Product {
    return Product(
        name = this.name,
        price = this.price,
        priceOfert = this.price,
        priceNoOfert = this.priceWithoutOfert,
        ofertExtra = null,
        pricePerUnit = this.pricePerQuantity,
        pricePerUnitOfert = null,
        pricePerUnitText = this.pricePerQuantityText,
        pricePerUnitOfertText = null,
        imageUrl = this.imageUrl,
        productUrl = this.productUrl,
        hasOferta = this.hasOferta,
        hasOfertaExtra = false,
        tiendaIcon = this.tiendaIcon
    )
}

fun List<EroskiProduct>.mapToProductList(): List<Product> {
    return this.map { it.mapToProduct() }
}