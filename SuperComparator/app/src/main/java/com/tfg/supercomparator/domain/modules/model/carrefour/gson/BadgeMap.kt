package com.tfg.supercomparator.domain.modules.model.carrefour.gson


import com.google.gson.annotations.SerializedName

data class BadgeMap(
    @SerializedName("promotions")
    val promotions: List<Promotion>
)