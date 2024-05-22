package com.tfg.supercomparator.domain.modules.model.carrefour.gson


import com.google.gson.annotations.SerializedName

data class Seo(
    @SerializedName("keywords")
    val keywords: Any,
    @SerializedName("snippet")
    val snippet: String,
)