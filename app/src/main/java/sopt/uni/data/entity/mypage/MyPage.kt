package sopt.uni.data.entity.history

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class MyPage(
    val userId: Int = 0,
    val nickname: String = "",
    val image: String? = "",
    val startDate: String = "",
) : Parcelable
