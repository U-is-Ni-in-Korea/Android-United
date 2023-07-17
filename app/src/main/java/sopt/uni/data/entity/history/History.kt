package sopt.uni.data.entity.history

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class History(
    val date: String = "",
    val image: String = "",
    val title: String = "",
    val result: String = "",
    val winner: String = "",
    val myMission: Mission = Mission(),
    val partnerMission: Mission = Mission(),
) : Parcelable
