package com.tfg.supercomparator.domain.modules.model.carrefour.gson


import com.google.gson.annotations.SerializedName

data class InfoTag(
    @SerializedName("color_code")
    val colorCode: String,
    @SerializedName("message")
    val message: String
)