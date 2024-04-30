package com.tfg.supercomparator.domain.modules.model.carrefour.gson


import com.google.gson.annotations.SerializedName

data class Analytics(
    @SerializedName("impressions")
    val impressions: String,
    @SerializedName("virtualPage-Empathy")
    val virtualPageEmpathy: VirtualPageEmpathy
)