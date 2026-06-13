package com.hardcopy.retrowatch.ui.main

import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import com.google.android.material.snackbar.Snackbar
import com.hardcopy.retrowatch.databinding.ActivityRetroWatchBinding
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch
import timber.log.Timber

/**
 * Main activity - modernized with MVVM and ViewBinding
 * Replaces the old RetroWatchActivity with modern patterns
 */
@AndroidEntryPoint
class RetroWatchActivity : AppCompatActivity() {

    private val viewModel: RetroWatchViewModel by viewModels()
    private lateinit var binding: ActivityRetroWatchBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        
        // Use ViewBinding instead of findViewById
        binding = ActivityRetroWatchBinding.inflate(layoutInflater)
        setContentView(binding.root)
        
        setupToolbar()
        observeViewModel()
        setupClickListeners()
    }

    private fun setupToolbar() {
        setSupportActionBar(binding.toolbar)
        supportActionBar?.title = "RetroWatch"
    }

    private fun observeViewModel() {
        // Observe UI State
        lifecycleScope.launch {
            repeatOnLifecycle(Lifecycle.State.STARTED) {
                viewModel.uiState.observe(this@RetroWatchActivity) { state ->
                    when (state) {
                        UiState.Idle -> {
                            binding.progressBar.hide()
                        }
                        UiState.Loading -> {
                            binding.progressBar.show()
                        }
                        is UiState.Error -> {
                            binding.progressBar.hide()
                            showError(state.message)
                        }
                    }
                }
            }
        }

        // Observe Bluetooth Status
        viewModel.bluetoothStatus.observe(this) { status ->
            updateBluetoothStatusUI(status)
        }

        // Observe Notifications
        viewModel.notifications.observe(this) { notifications ->
            Timber.d("Notifications updated: ${notifications.size}")
            // Update UI with notifications
        }

        // Observe Errors
        viewModel.error.observe(this) { error ->
            if (error != null) {
                showError(error)
                viewModel.clearError()
            }
        }
    }

    private fun setupClickListeners() {
        binding.btnConnect.setOnClickListener {
            // TODO: Show device list and connect
        }

        binding.btnDisconnect.setOnClickListener {
            viewModel.disconnectDevice()
        }
    }

    private fun updateBluetoothStatusUI(status: BluetoothStatus) {
        val statusText = when (status) {
            BluetoothStatus.Disconnected -> "Disconnected"
            BluetoothStatus.Ready -> "Ready"
            BluetoothStatus.Connecting -> "Connecting..."
            BluetoothStatus.Connected -> "Connected"
            BluetoothStatus.Error -> "Error"
        }
        binding.tvBluetoothStatus.text = statusText
    }

    private fun showError(message: String) {
        Snackbar.make(binding.root, message, Snackbar.LENGTH_SHORT).show()
        Timber.e("UI Error: $message")
    }
}
