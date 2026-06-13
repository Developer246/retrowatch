package com.hardcopy.retrowatch.ui.base

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlin.coroutines.CoroutineContext

/**
 * Base ViewModel class with coroutine support
 * Provides common functionality for all ViewModels
 */
open class BaseViewModel : ViewModel(), CoroutineScope {
    private val job = Job()
    override val coroutineContext: CoroutineContext
        get() = job + Dispatchers.Main.immediate

    override fun onCleared() {
        super.onCleared()
        job.cancel()
    }
}
