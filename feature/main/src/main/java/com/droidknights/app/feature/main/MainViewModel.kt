package com.droidknights.app.feature.main

import com.droidknights.app.core.data.settings.api.SettingsRepository
import com.droidknights.app.core.ui.BaseViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
internal class MainViewModel @Inject constructor(
    settingsRepository: SettingsRepository
) : BaseViewModel() {

    val isDarkTheme = settingsRepository.flowIsDarkTheme()
}
