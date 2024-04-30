package com.tfg.supercomparator.domain.modules.model.hipercor

import com.tfg.supercomparator.domain.modules.model.product.Product

data class Hipercor(
    val nombre: String,
    val precio: Double,
    val precioPorUnidad: Double,
    val precioPorUnidadText: String,
    val precioSinOferta: Double?,
    val imageUrl: String,
    val productUrl: String,
    val haveOferta: Boolean = precioSinOferta != null
) {
    fun Hipercor.mapToProduct(): Product {
        return Product(
            name = this.nombre,
            price = this.precio,
            priceOfert = this.precioSinOferta,
            priceNoOfert = null,
            ofertExtra = null,
            pricePerUnit = this.precioPorUnidad,
            pricePerUnitOfert = null,
            pricePerUnitText = this.precioPorUnidadText,
            pricePerUnitOfertText = null,
            imageUrl = this.imageUrl,
            productUrl = this.productUrl,
            hasOferta = this.haveOferta,
            hasOfertaExtra = false
        )
    }

    fun List<Hipercor>.mapToProductList(): List<Product> {
        return this.map { it.mapToProduct() }
    }
}
