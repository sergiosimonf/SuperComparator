package com.tfg.supercomparator.domain.modules.model.carrefour.gson


import com.google.gson.annotations.SerializedName

data class Badge(
    @SerializedName("name")
    val name: String,
)