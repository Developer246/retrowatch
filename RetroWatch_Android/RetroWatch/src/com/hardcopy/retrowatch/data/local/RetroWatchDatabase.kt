package com.hardcopy.retrowatch.data.local

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.hardcopy.retrowatch.data.model.NotificationEntity

/**
 * Room database for RetroWatch app
 */
@Database(
    entities = [NotificationEntity::class],
    version = 1,
    exportSchema = true
)
abstract class RetroWatchDatabase : RoomDatabase() {
    abstract fun notificationDao(): NotificationDao

    companion object {
        private var instance: RetroWatchDatabase? = null
        private val lock = Any()

        fun getInstance(context: Context): RetroWatchDatabase {
            return instance ?: synchronized(lock) {
                val db = Room.databaseBuilder(
                    context.applicationContext,
                    RetroWatchDatabase::class.java,
                    "retrowatch.db"
                )
                    .fallbackToDestructiveMigration()
                    .build()
                instance = db
                db
            }
        }
    }
}
