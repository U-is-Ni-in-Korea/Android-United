package sopt.uni.presentation.entity

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class MissionIdPosition(
    val id: Int,
    val position: Int,
) : Parcelable
