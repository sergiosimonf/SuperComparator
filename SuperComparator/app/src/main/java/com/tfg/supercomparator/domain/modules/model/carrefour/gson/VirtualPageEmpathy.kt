package com.tfg.supercomparator.domain.modules.model.carrefour.gson


import com.google.gson.annotations.SerializedName

data class VirtualPageEmpathy(
    @SerializedName("addtoUrl")
    val addtoUrl: String,
    @SerializedName("event")
    val event: String,
    @SerializedName("hashedSessionId")
    val hashedSessionId: String,
    @SerializedName("pageRefinementType")
    val pageRefinementType: String,
    @SerializedName("pageRefinementTypeSort")
    val pageRefinementTypeSort: String,
    @SerializedName("pageType")
    val pageType: String,
    @SerializedName("searchNumResults")
    val searchNumResults: String,
    @SerializedName("searchParamsQuery")
    val searchParamsQuery: String,
)