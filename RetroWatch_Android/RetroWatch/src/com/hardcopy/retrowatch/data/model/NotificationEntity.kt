package com.hardcopy.retrowatch.data.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

/**
 * Room database entity for notifications
 */
@Entity(tableName = "notifications")
data class NotificationEntity(
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,

    @ColumnInfo(name = "title")
    val title: String,

    @ColumnInfo(name = "message")
    val message: String,

    @ColumnInfo(name = "timestamp")
    val timestamp: Long = System.currentTimeMillis(),

    @ColumnInfo(name = "type")
    val type: Int = 0, // 0: Normal, 1: Emergency

    @ColumnInfo(name = "package_name")
    val packageName: String = "",

    @ColumnInfo(name = "is_sent")
    val isSent: Boolean = false
)
