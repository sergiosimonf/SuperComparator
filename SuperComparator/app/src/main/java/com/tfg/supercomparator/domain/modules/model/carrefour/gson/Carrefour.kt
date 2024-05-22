package com.tfg.supercomparator.domain.modules.model.carrefour.gson


import com.google.gson.annotations.SerializedName

data class Carrefour(
    @SerializedName("analytics")
    val analytics: Analytics,
    @SerializedName("banner")
    val banner: List<Any>,
    @SerializedName("content")
    val content: Content,
    @SerializedName("direct")
    val direct: List<Any>,
    @SerializedName("promoted")
    val promoted: List<Any>,
    @SerializedName("seo")
    val seo: Seo,
)