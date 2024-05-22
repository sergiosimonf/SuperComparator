package com.tfg.supercomparator.domain.modules.model.product

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class Product(
    @PrimaryKey
    val name: String,
    val price: Double,
    val priceOfert: Double?,
    val priceNoOfert: Double?,
    val ofertExtra: String?,
    val pricePerUnit: Double?,
    val pricePerUnitOfert: Double?,
    val pricePerUnitText: String,
    val pricePerUnitOfertText: String?,
    val imageUrl: String,
    val productUrl: String?,
    val hasOferta: Boolean,
    val hasOfertaExtra: Boolean,
    val tiendaIcon: Int,
    var isFavorite: Boolean = false
)
