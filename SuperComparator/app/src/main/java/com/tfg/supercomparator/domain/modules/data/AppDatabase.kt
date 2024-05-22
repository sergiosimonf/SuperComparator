package com.tfg.supercomparator.domain.modules.data

import androidx.room.Database
import androidx.room.RoomDatabase
import com.tfg.supercomparator.domain.modules.model.product.Product

@Database(
    entities = [Product::class],
    version = 1
)
abstract class AppDatabase : RoomDatabase() {
    abstract val productDAO: ProductDAO
}