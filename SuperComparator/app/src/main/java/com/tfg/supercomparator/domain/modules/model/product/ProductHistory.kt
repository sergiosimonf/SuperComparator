package com.tfg.supercomparator.domain.modules.model.product

import java.time.LocalDate

data class ProductHistory(
    private val history: Map<String, Float>,
) {
    companion object {
        fun ProductHistory.mapToLocalDateString(): Map<LocalDate, Float> {
            return this.history.mapKeys {
                LocalDate.parse(it.key)
            }
        }
    }
}

