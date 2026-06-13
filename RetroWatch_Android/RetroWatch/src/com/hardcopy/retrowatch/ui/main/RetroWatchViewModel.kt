package com.hardcopy.retrowatch.ui.main

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.hardcopy.retrowatch.data.repository.BluetoothRepository
import com.hardcopy.retrowatch.data.repository.NotificationRepository
import com.hardcopy.retrowatch.ui.base.BaseViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import timber.log.Timber
import javax.inject.Inject

/**
 * ViewModel for RetroWatch main activity
 * Manages UI state, Bluetooth connectivity, and notifications
 */
@HiltViewModel
class RetroWatchViewModel @Inject constructor(
    private val bluetoothRepository: BluetoothRepository,
    private val notificationRepository: NotificationRepository
) : BaseViewModel() {

    // UI State
    private val _uiState = MutableLiveData<UiState>(UiState.Idle)
    val uiState: LiveData<UiState> = _uiState

    private val _bluetoothStatus = MutableLiveData<BluetoothStatus>(BluetoothStatus.Disconnected)
    val bluetoothStatus: LiveData<BluetoothStatus> = _bluetoothStatus

    private val _notifications = MutableLiveData<List<NotificationItem>>(emptyList())
    val notifications: LiveData<List<NotificationItem>> = _notifications

    private val _error = MutableLiveData<String?>()
    val error: LiveData<String?> = _error

    init {
        initializeBluetooth()
        loadNotifications()
    }

    private fun initializeBluetooth() {
        viewModelScope.launch {
            try {
                _uiState.value = UiState.Loading
                val isEnabled = bluetoothRepository.isBluetoothEnabled()
                if (isEnabled) {
                    _bluetoothStatus.value = BluetoothStatus.Ready
                    _uiState.value = UiState.Idle
                } else {
                    _uiState.value = UiState.Error("Bluetooth not enabled")
                }
            } catch (e: Exception) {
                Timber.e(e, "Failed to initialize Bluetooth")
                _error.value = e.message
                _uiState.value = UiState.Error(e.message ?: "Unknown error")
            }
        }
    }

    private fun loadNotifications() {
        viewModelScope.launch {
            try {
                notificationRepository.getNotifications().collect { notifications ->
                    _notifications.value = notifications
                }
            } catch (e: Exception) {
                Timber.e(e, "Failed to load notifications")
                _error.value = e.message
            }
        }
    }

    fun connectToDevice(deviceAddress: String) {
        viewModelScope.launch {
            try {
                _uiState.value = UiState.Loading
                bluetoothRepository.connectDevice(deviceAddress)
                _bluetoothStatus.value = BluetoothStatus.Connected
                _uiState.value = UiState.Idle
            } catch (e: Exception) {
                Timber.e(e, "Failed to connect to device")
                _error.value = e.message
                _uiState.value = UiState.Error(e.message ?: "Connection failed")
            }
        }
    }

    fun disconnectDevice() {
        viewModelScope.launch {
            try {
                bluetoothRepository.disconnect()
                _bluetoothStatus.value = BluetoothStatus.Disconnected
            } catch (e: Exception) {
                Timber.e(e, "Failed to disconnect")
                _error.value = e.message
            }
        }
    }

    fun clearError() {
        _error.value = null
    }
}

// UI State sealed class
sealed class UiState {
    object Idle : UiState()
    object Loading : UiState()
    data class Error(val message: String) : UiState()
}

// Bluetooth Status
enum class BluetoothStatus {
    Disconnected, Ready, Connecting, Connected, Error
}

// Notification Item data class
data class NotificationItem(
    val id: Int,
    val title: String,
    val message: String,
    val timestamp: Long,
    val type: Int
)
