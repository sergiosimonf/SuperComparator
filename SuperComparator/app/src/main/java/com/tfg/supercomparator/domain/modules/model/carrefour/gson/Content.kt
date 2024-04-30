package com.tfg.supercomparator.domain.modules.model.carrefour.gson


import com.google.gson.annotations.SerializedName

data class Content(
    @SerializedName("content")
    val content: Any,
    @SerializedName("docs")
    val docs: List<Doc>,
    @SerializedName("ebTagging")
    val ebTagging: EbTaggingX,
    @SerializedName("facets")
    val facets: List<Facet>,
    @SerializedName("numFound")
    val numFound: Int
)