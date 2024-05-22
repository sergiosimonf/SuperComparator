package com.tfg.supercomparator.domain.modules.model.alcampo.gson

data class Alcampo(
    val productId: String,
    val retailerProductId: String,
    val name: String,
    val available: Boolean,
    val maxQuantityReached: Boolean,
    val alternatives: List<Any>,
    val price: Price,
    val isInCurrentCatalog: Boolean,
    val isInProductList: Boolean,
    val categoryPath: List<String>,
    val brand: String,
    val ratingSummary: RatingSummary,
    val retailerFinancingPlanIds: List<String>,
    val image: Image,
    val images: List<Image>,
    val icons: Icons,
    val size: Size,
    val featured: String,
)

data class Price(
    val current: Amount,
    val unit: Unit,
)

data class Amount(
    val amount: String,
    val currency: String,
)

data class Unit(
    val label: String,
    val current: Amount,
)

data class RatingSummary(
    val overallRating: String,
    val count: Int,
)

data class Image(
    val src: String,
    val description: String,
)

data class Icons(
    val certification: List<Any>, // Puedes reemplazar Any con el tipo de objeto adecuado si lo conoces
    val legal: List<Any>, // Puedes reemplazar Any con el tipo de objeto adecuado si lo conoces
)

data class Size(
    val value: String,
)

