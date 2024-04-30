package com.tfg.supercomparator.domain.modules.model.carrefour.gson


import com.google.gson.annotations.SerializedName

data class EbTagging(
    @SerializedName("add2cart")
    val add2cart: String,
    @SerializedName("click")
    val click: String
)