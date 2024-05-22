package com.tfg.supercomparator.domain.modules.data

import android.content.Context
import androidx.room.Room

class DatabaseProvider {
    companion object {
        private var databaseInstance: AppDatabase? = null

        fun getAppDatabase(context: Context): AppDatabase {
            if (databaseInstance == null) {
                databaseInstance = Room.databaseBuilder(
                    context.applicationContext,
                    AppDatabase::class.java,
                    "database-SuperComparator"
                ).fallbackToDestructiveMigration().build()
            }
            return databaseInstance as AppDatabase
        }

        fun destroyDatabase() {
            databaseInstance = null
        }
    }
}