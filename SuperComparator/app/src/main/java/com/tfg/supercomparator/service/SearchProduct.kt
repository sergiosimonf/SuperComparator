package com.tfg.supercomparator.service

import com.tfg.supercomparator.domain.modules.model.product.ProductHistoryItems
import retrofit2.Response

interface SearchProduct<T> {
    suspend fun findProducts(query: String): List<T>
    suspend fun saveProductHistory(product: List<T>): Response<ProductHistoryItems>
}