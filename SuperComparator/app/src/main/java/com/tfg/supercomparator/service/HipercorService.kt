package com.tfg.supercomparator.service

import com.tfg.supercomparator.domain.modules.model.hipercor.HipercorProduct

class HipercorService : SearchProduct<HipercorProduct> {
    override suspend fun findProducts(query: String): List<HipercorProduct> {
        TODO("Not yet implemented")
    }
}