package com.hardcopy.retrowatch.di

import android.content.Context
import com.hardcopy.retrowatch.data.local.NotificationDao
import com.hardcopy.retrowatch.data.local.RetroWatchDatabase
import com.hardcopy.retrowatch.data.repository.BluetoothRepository
import com.hardcopy.retrowatch.data.repository.NotificationRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

/**
 * Hilt module for repository dependency injection
 */
@Module
@InstallIn(SingletonComponent::class)
object RepositoryModule {

    @Singleton
    @Provides
    fun provideRetroWatchDatabase(
        @ApplicationContext context: Context
    ): RetroWatchDatabase {
        return RetroWatchDatabase.getInstance(context)
    }

    @Singleton
    @Provides
    fun provideNotificationDao(
        database: RetroWatchDatabase
    ): NotificationDao {
        return database.notificationDao()
    }

    @Singleton
    @Provides
    fun provideNotificationRepository(
        notificationDao: NotificationDao
    ): NotificationRepository {
        return NotificationRepository(notificationDao)
    }

    @Singleton
    @Provides
    fun provideBluetoothRepository(
        @ApplicationContext context: Context
    ): BluetoothRepository {
        return BluetoothRepository(context)
    }
}
