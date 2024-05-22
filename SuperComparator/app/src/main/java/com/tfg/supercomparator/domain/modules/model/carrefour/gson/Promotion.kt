package com.tfg.supercomparator.domain.modules.model.carrefour.gson


import com.google.gson.annotations.SerializedName

data class Promotion(
    @SerializedName("description")
    val description: String,
    @SerializedName("name")
    val name: String,
    @SerializedName("url")
    val url: String,
)