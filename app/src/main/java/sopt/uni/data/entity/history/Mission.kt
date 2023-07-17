package sopt.uni.data.entity.history

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class Mission(
    val content: String = "",
    val result: String = "",
    val time: String = "",
) : Parcelable
