package sopt.uni.presentation.memo

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import sopt.uni.data.repository.shortgame.ShortGameRepository
import javax.inject.Inject

@HiltViewModel
class MemoViewModel @Inject constructor(
    private val shortGameRepository: ShortGameRepository,
) : ViewModel() {
    val memoText = MutableLiveData<String>("")

    init {
        memoText.value = shortGameRepository.getMemoText()
    }

    private fun saveMemoText() {
        shortGameRepository.setMemoText(memoText.value ?: "")
    }

    override fun onCleared() {
        saveMemoText()
    }
}
