package com.example.database.di

import android.content.Context
import android.content.res.AssetManager
import androidx.room.Room
import com.example.database.AppDatabase
import dagger.Module
import dagger.Provides
import kotlinx.serialization.json.Json

@Module
class DatabaseModule {

    @Provides
    fun providesAppDatabase(
        context: Context,
    ): AppDatabase = Room.databaseBuilder(
        context,
        AppDatabase::class.java,
        "app_database"
    ).build()

    @Provides
    fun providesNetworkJson(): Json = Json {
        ignoreUnknownKeys = true
    }

    @Provides
    fun providesAssetManager(
        context: Context,
    ): AssetManager = context.assets
}
