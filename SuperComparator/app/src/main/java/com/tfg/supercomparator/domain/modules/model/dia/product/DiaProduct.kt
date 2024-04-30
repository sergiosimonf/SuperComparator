package com.tfg.supercomparator.domain.modules.model.dia.product

import com.tfg.supercomparator.domain.modules.model.product.Product

data class DiaProduct(
    val name: String,
    val price: Double,
    val priceSinOferta: Double,
    val pricePerUnit: Double,
    val pricePerUnitText: String,
    val imageUrl: String,
    val productUrl: String,
    val hadOferta: Boolean = price.compareTo(priceSinOferta) != 0
) {
    fun DiaProduct.mapToProduct(): Product {
        return Product(
            name = this.name,
            price = this.price,
            priceOfert = if (this.hadOferta) this.priceSinOferta else null,
            priceNoOfert = if (!this.hadOferta) this.priceSinOferta else null,
            ofertExtra = null,
            pricePerUnit = this.pricePerUnit,
            pricePerUnitOfert = null,
            pricePerUnitText = this.pricePerUnitText,
            pricePerUnitOfertText = null,
            imageUrl = this.imageUrl,
            productUrl = this.productUrl,
            hasOferta = this.hadOferta,
            hasOfertaExtra = false
        )
    }

    fun List<DiaProduct>.mapToProductList(): List<Product> {
        return this.map { it.mapToProduct() }
    }
}