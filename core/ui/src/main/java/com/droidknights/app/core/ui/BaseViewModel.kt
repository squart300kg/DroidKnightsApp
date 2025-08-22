package com.droidknights.app.core.ui

import androidx.annotation.VisibleForTesting
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.droidknights.app.core.router.api.Navigator
import com.droidknights.app.core.router.api.model.Route
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
open class BaseViewModel @Inject constructor() : ViewModel() {
    protected val internalErrorFlow = MutableSharedFlow<Throwable>()
    val errorFlow = internalErrorFlow.asSharedFlow()

    @Inject
    lateinit var navigator: Navigator

    @VisibleForTesting
    fun injectNavigator(navigator: Navigator) {
        this.navigator = navigator
    }

    fun navigateBack() = viewModelScope.launch {
        navigator.navigateBack()
    }

    fun navigateWeb(url: String) = viewModelScope.launch {
        navigator.navigateWeb(url)
    }

    fun navigateTo(
        route: Route,
        saveState: Boolean = false,
        launchSingleTop: Boolean = false
    ) = viewModelScope.launch {
            navigator.navigate(route, saveState, launchSingleTop)
    }
}
