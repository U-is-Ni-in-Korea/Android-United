package sopt.uni.presentation.wish

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import sopt.uni.data.repository.wish.WishRepository
import javax.inject.Inject

@HiltViewModel
class WishFcViewModel @Inject constructor(
    private val wishRepository: WishRepository,
) : ViewModel() {
    private val _wishCouponContent = MutableLiveData("")
    val wishCouponContent: LiveData<String?> = _wishCouponContent

    private val _wishCouponIsUsed = MutableLiveData(true)
    val wishCouponIsUsed: LiveData<Boolean> = _wishCouponIsUsed

    private val _wishCouponImage = MutableLiveData("")
    val wishCouponImage: LiveData<String> = _wishCouponImage

    private val _isMine = MutableLiveData<Boolean>(true)
    val isMine: LiveData<Boolean> = _isMine

    private val _nickname = MutableLiveData<String>()
    val nickname: LiveData<String> = _nickname

    fun setWishDetailData(wishCouponId: Int) {
        viewModelScope.launch {
            wishRepository.getWishDetail(wishCouponId)
                .onSuccess {
                    _isMine.value = it.isMine
                    _nickname.value = it.nickname
                    _wishCouponContent.value = it.wishCoupon.content
                    _wishCouponIsUsed.value = it.wishCoupon.isUsed
                    _wishCouponImage.value = it.wishCoupon.image
                }.onFailure {
                    // TODO: 에러 처리
                }
        }
    }

    fun createWishData(gameType: String, content: String) {
        viewModelScope.launch {
            wishRepository.patchCreateWish(gameType, content)
        }
    }

    fun useWishData(wishCouponId: Int) {
        viewModelScope.launch {
            wishRepository.patchUseWish(wishCouponId)
        }
    }
}
