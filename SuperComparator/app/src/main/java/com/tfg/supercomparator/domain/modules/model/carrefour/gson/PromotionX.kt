package com.tfg.supercomparator.domain.modules.model.carrefour.gson


import com.google.gson.annotations.SerializedName

data class PromotionX(
    @SerializedName("id")
    val id: String,
    @SerializedName("url")
    val url: String,
)