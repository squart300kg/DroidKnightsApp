package com.droidknights.app.feature.contributor

import androidx.lifecycle.viewModelScope
import com.droidknights.app.core.domain.contributor.usecase.api.GetContributorsUseCase
import com.droidknights.app.core.ui.BaseViewModel
import com.droidknights.app.feature.contributor.model.ContributorsUiState
import com.droidknights.app.feature.contributor.model.convert.toContributorsUiState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ContributorViewModel @Inject constructor(
    getContributorsUseCase: GetContributorsUseCase
) : BaseViewModel() {

    val uiState: StateFlow<ContributorsUiState> by lazy {
        getContributorsUseCase()
            .map {
                it.toContributorsUiState()
            }
            .catch { throwable ->
                _errorFlow.emit(throwable)
            }
            .stateIn(
                scope = viewModelScope,
                started = SharingStarted.WhileSubscribed(5_000),
                initialValue = ContributorsUiState.Loading
            )
    }
}
