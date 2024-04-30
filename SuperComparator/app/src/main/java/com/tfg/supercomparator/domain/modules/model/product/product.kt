package com.tfg.supercomparator.domain.modules.model.product

data class Product(
    val name: String,
    val price: Double,
    val priceOfert: Double?,
    val priceNoOfert: Double?,
    val ofertExtra: String?,
    val pricePerUnit: Double,
    val pricePerUnitOfert: Double?,
    val pricePerUnitText: String,
    val pricePerUnitOfertText: String?,
    val imageUrl: String,
    val productUrl: String?,
    val hasOferta: Boolean,
    val hasOfertaExtra: Boolean
)
