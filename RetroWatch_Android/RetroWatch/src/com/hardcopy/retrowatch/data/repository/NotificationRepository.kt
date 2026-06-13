package com.hardcopy.retrowatch.data.repository

import com.hardcopy.retrowatch.data.local.NotificationDao
import com.hardcopy.retrowatch.data.model.NotificationEntity
import kotlinx.coroutines.flow.Flow
import timber.log.Timber
import javax.inject.Inject
import javax.inject.Singleton

/**
 * Repository for notification data
 * Handles local storage and retrieval of notifications
 */
@Singleton
class NotificationRepository @Inject constructor(
    private val notificationDao: NotificationDao
) {

    fun getNotifications(): Flow<List<NotificationEntity>> {
        return try {
            notificationDao.getAllNotifications()
        } catch (e: Exception) {
            Timber.e(e, "Failed to get notifications")
            throw e
        }
    }

    fun getNotificationById(id: Int): Flow<NotificationEntity?> {
        return notificationDao.getNotificationById(id)
    }

    suspend fun addNotification(notification: NotificationEntity) {
        try {
            notificationDao.insertNotification(notification)
            Timber.d("Notification added: ${notification.title}")
        } catch (e: Exception) {
            Timber.e(e, "Failed to add notification")
            throw e
        }
    }

    suspend fun deleteNotification(id: Int) {
        try {
            notificationDao.deleteNotificationById(id)
            Timber.d("Notification deleted: $id")
        } catch (e: Exception) {
            Timber.e(e, "Failed to delete notification")
            throw e
        }
    }

    suspend fun clearAllNotifications() {
        try {
            notificationDao.deleteAllNotifications()
            Timber.d("All notifications cleared")
        } catch (e: Exception) {
            Timber.e(e, "Failed to clear notifications")
            throw e
        }
    }
}
