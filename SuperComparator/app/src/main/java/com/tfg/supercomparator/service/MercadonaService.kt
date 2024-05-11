package com.tfg.supercomparator.service

import com.tfg.supercomparator.domain.modules.model.mercadona.MercadonaProduct

class MercadonaService: SearchProduct<MercadonaProduct> {
    override suspend fun findProducts(query: String): List<MercadonaProduct> {
        TODO("Not yet implemented")
    }
}