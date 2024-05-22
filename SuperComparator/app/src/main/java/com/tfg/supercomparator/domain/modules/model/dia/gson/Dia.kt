package com.tfg.supercomparator.domain.modules.model.dia.gson

data class Dia(
    val cart: Cart,
    val facets: List<Facet>,
    val locale: String,
    val login_status: String,
    val pagination: Pagination,
    val query_id: String,
    val search_items: List<SearchItem>,
    val sort: Sort,
    val suggestions: Suggestions,
    val total_items: Int,
)