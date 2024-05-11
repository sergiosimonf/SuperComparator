package com.tfg.supercomparator.service

interface SearchProduct<T> {
    suspend fun findProducts(query: String): List<T>
}