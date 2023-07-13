package sopt.uni.data.entity.history

import androidx.annotation.DrawableRes

data class HistoryItem(
    val date: String,
    @DrawableRes
    val result_img: Int,
    val title: String,
    val result: String,
    @DrawableRes
    val next: Int,
)
