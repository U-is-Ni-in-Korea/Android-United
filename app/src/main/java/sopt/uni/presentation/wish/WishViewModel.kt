package sopt.uni.presentation.wish

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import sopt.uni.data.repository.wish.WishRepository
import sopt.uni.data.source.remote.response.WishCoupon
import javax.inject.Inject

@HiltViewModel
class WishViewModel @Inject constructor(
    private val wishRepository: WishRepository,
) : ViewModel() {

    private val _availableWishCoupon = MutableLiveData<Int>()
    val availableWishCoupon: LiveData<Int> = _availableWishCoupon

    private val _wishCouponList = MutableLiveData<List<WishCoupon>>()
    val wishCouponList: LiveData<List<WishCoupon>> = _wishCouponList

    private val _newWishCoupon = MutableLiveData<Int>()
    val newWishCoupon: LiveData<Int> = _newWishCoupon

    private val _isMineState = MutableLiveData<Boolean>(true)
    val isMineState: LiveData<Boolean> = _isMineState

    init {
    }

    fun setWishData(userId: Int) {
        viewModelScope.launch {
            wishRepository.getWishList(userId).onSuccess {
                _availableWishCoupon.value = it.availableWishCoupon
                _wishCouponList.value = it.wishCouponList
                _newWishCoupon.value = it.newWishCoupon
            }.onFailure {
                // TODO: 실패 처리
            }
        }
    }

    fun getWishData(userId: Int) {
        viewModelScope.launch {
            wishRepository.getWishList(userId)
                .onSuccess {
                    _wishCouponList.value = it.wishCouponList
                }.onFailure {
                    // TODO: 에러 처리
                }
        }
    }

    fun getMyWishList(myId: Int) {
        _isMineState.postValue(true)
        getWishData(myId)
    }

    fun getPartnerWishList(partnerId: Int) {
        _isMineState.postValue(false)
        getWishData(partnerId)
    }
}
