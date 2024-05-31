package com.tfg.supercomparator.domain.modules.model.hipercor

import com.tfg.supercomparator.R
import com.tfg.supercomparator.domain.modules.model.product.ItemProductHistory
import com.tfg.supercomparator.domain.modules.model.product.Product
import com.tfg.supercomparator.domain.modules.model.product.ProductHistoryItems
import java.time.LocalDate

data class HipercorProduct(
    val nombre: String,
    val precio: Double,
    val precioPorUnidad: Double,
    val precioPorUnidadText: String,
    val precioSinOferta: Double?,
    val imageUrl: String,
    val productUrl: String,
    val haveOferta: Boolean = precioSinOferta != null,
    val tiendaIcon: Int = R.drawable.hipercor,
)

fun HipercorProduct.mapToProduct(): Product {
    return Product(
        name = this.nombre,
        price = this.precio,
        priceOfert = this.precio,
        priceNoOfert = this.precioSinOferta,
        ofertExtra = null,
        pricePerUnit = this.precioPorUnidad,
        pricePerUnitOfert = null,
        pricePerUnitText = this.precioPorUnidadText,
        pricePerUnitOfertText = null,
        imageUrl = this.imageUrl,
        productUrl = this.productUrl,
        hasOferta = this.haveOferta,
        hasOfertaExtra = false,
        tiendaIcon = R.drawable.hipercor
    )
}

fun List<HipercorProduct>.mapToProductList(): List<Product> {
    return this.map { it.mapToProduct() }
}

fun List<HipercorProduct>.toProductHistoryItems(): ProductHistoryItems {
    val items = this.map { product ->
        ItemProductHistory(
            nombre = product.nombre,
            price = product.precio.toFloat()
        )
    }
    return ProductHistoryItems(
        fecha = LocalDate.now().toString(),
        products = items
    )
}
