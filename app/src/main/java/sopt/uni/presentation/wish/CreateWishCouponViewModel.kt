package sopt.uni.presentation.wish

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import sopt.uni.data.repository.wish.WishRepository
import timber.log.Timber
import javax.inject.Inject

@HiltViewModel
class CreateWishCouponViewModel @Inject constructor(
    private val wishRepository: WishRepository,
) : ViewModel() {

    fun patchCreateWishCoupon(content: String) {
        viewModelScope.launch {
            kotlin.runCatching {
                wishRepository.patchCreateWish("SHORT", content)
            }.fold({
                Timber.d("소원권 생성 완료")
            }, {
                Timber.d(it)
            })
        }
    }
}
