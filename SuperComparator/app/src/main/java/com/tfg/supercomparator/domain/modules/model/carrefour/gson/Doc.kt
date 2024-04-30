package com.tfg.supercomparator.domain.modules.model.carrefour.gson


import com.google.gson.annotations.SerializedName

data class Doc(
    @SerializedName("active_price")
    val activePrice: Double,
    @SerializedName("app_price")
    val appPrice: Double,
    @SerializedName("app_strikethrough_price")
    val appStrikethroughPrice: String,
    @SerializedName("app_strikethrough_price_per_unit")
    val appStrikethroughPricePerUnit: String,
    @SerializedName("average_weight")
    val averageWeight: Int,
    @SerializedName("badge")
    val badge: Badge?,
    @SerializedName("badge_map")
    val badgeMap: BadgeMap,
    @SerializedName("brand")
    val brand: String,
    @SerializedName("catalog_ref_id")
    val catalogRefId: String,
    @SerializedName("color_rollup")
    val colorRollup: String,
    @SerializedName("display_name")
    val displayName: String,
    @SerializedName("document_type")
    val documentType: String,
    @SerializedName("ean13")
    val ean13: String,
    @SerializedName("ebTagging")
    val ebTagging: EbTagging,
    @SerializedName("image_path")
    val imagePath: String,
    @SerializedName("info_tags")
    val infoTags: List<InfoTag>,
    @SerializedName("list_price")
    val listPrice: Double,
    @SerializedName("measure_unit")
    val measureUnit: String,
    @SerializedName("num_images")
    val numImages: Int,
    @SerializedName("price_per_unit_text")
    val pricePerUnitText: String,
    @SerializedName("product_id")
    val productId: String,
    @SerializedName("promotion")
    val promotion: PromotionX,
    @SerializedName("section")
    val section: String,
    @SerializedName("sell_pack_unit")
    val sellPackUnit: Int,
    @SerializedName("stock")
    val stock: Int,
    @SerializedName("strikethrough_price")
    val strikethroughPrice: Double,
    @SerializedName("strikethrough_price_per_unit")
    val strikethroughPricePerUnit: Double,
    @SerializedName("unit_conversion_factor")
    val unitConversionFactor: Double,
    @SerializedName("url")
    val url: String
)