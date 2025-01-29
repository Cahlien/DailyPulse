package com.petros.efthymiou.dailypulse

import androidx.lifecycle.ViewModel

actual open class BaseViewModel: ViewModel {
    actual val scope = viewModelScope
}