package com.tfg.supercomparator.domain.modules.model.dia.gson

data class Prices(
    val currency: String,
    val discount_percentage: Int,
    val is_club_price: Boolean,
    val is_promo_price: Boolean,
    val measure_unit: String,
    val price: Double,
    val price_per_unit: Double,
    val strikethrough_price: Double
)