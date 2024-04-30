package com.tfg.supercomparator.domain.modules.model.dia.gson

data class SearchItem(
    val brand: String,
    val brand_type: String,
    val display_name: String,
    val image: String,
    val l1_category_description: String,
    val l2_category_description: String,
    val object_id: String,
    val prices: Prices,
    val sku_id: String,
    val units_in_cart: Int,
    val units_in_stock: Int,
    val url: String
)