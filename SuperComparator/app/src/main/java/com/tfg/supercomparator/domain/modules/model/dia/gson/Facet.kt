package com.tfg.supercomparator.domain.modules.model.dia.gson

data class Facet(
    val `field`: String,
    val field_name: String,
    val filters: List<Filter>,
    val selected_count: Int,
)