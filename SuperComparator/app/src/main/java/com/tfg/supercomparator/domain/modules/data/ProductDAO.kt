package com.tfg.supercomparator.domain.modules.data

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Query
import androidx.room.Upsert
import com.tfg.supercomparator.domain.modules.model.product.Product
import kotlinx.coroutines.flow.Flow

@Dao
interface ProductDAO {
    @Upsert
    suspend fun upsertProduct(product: Product)

    @Upsert
    suspend fun upsertProducts(products: List<Product>)

    @Delete
    suspend fun deleteProduct(product: Product)

    @Query("SELECT * FROM product WHERE name = :name")
    suspend fun findByName(name: String): Product

    @Query("SELECT * FROM product")
    fun getAllProducts(): List<Product>

    @Query("SELECT * FROM product WHERE isFavorite = 1")
    fun getFavProducts(): List<Product>

    @Query("SELECT * FROM product ORDER BY price ASC")
    fun getProductsOrdererByPrice(): Flow<List<Product>>

    @Query("SELECT * FROM product ORDER BY pricePerUnit ASC")
    fun getProductsOrdererByPricePerUnit(): Flow<List<Product>>
}