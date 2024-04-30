package com.tfg.supercomparator.domain.modules.model.carrefour.gson


import com.google.gson.annotations.SerializedName

data class Facet(
    @SerializedName("facet")
    val facet: String,
    @SerializedName("facet_id")
    val facetId: String,
    @SerializedName("values")
    val values: List<Value>
)