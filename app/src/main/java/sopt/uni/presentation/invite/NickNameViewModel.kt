package sopt.uni.presentation.invite

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.debounce
import kotlinx.coroutines.flow.distinctUntilChanged
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn
import javax.inject.Inject

@HiltViewModel
class NickNameViewModel @Inject constructor() : ViewModel() {

    val nickName = MutableStateFlow("")

    val checkNickNameState: StateFlow<Boolean?> = nickName.debounce(300L)
        .distinctUntilChanged()
        .map { it.length in 1..10 }
        .stateIn(viewModelScope, SharingStarted.WhileSubscribed(), null)
}
