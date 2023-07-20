package sopt.uni.data.source.remote.response

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import sopt.uni.data.entity.home.HomeInfo

@Serializable
data class ResponseHomeDto(
    @SerialName("couple")
    val couple: Couple,
    @SerialName("dDay")
    val dDay: Int,
    @SerialName("drawCount")
    val drawCount: Int,
    @SerialName("myScore")
    val myScore: Int,
    @SerialName("partnerId")
    val partnerId: Int,
    @SerialName("partnerScore")
    val partnerScore: Int,
    @SerialName("roundGameId")
    val roundGameId: Int?,
    @SerialName("shortGame")
    val shortGame: ShortGame?,
    @SerialName("userId")
    val userId: Int,
) {
    @Serializable
    data class Couple(
        @SerialName("heartToken")
        val heartToken: Int,
        @SerialName("id")
        val id: Int,
        @SerialName("startDate")
        val startDate: String,
    )

    @Serializable
    data class ShortGame(
        @SerialName("enable")
        val enable: Boolean,
        @SerialName("finishAt")
        val finishAt: String,
        @SerialName("id")
        val id: Int,
    )

    fun toHomeInfo() = HomeInfo(
        roundGameId = roundGameId,
        myScore = myScore,
        partnerScore = partnerScore,
        drawCount = drawCount,
        dDay = dDay,
        enable = shortGame?.enable,
    )
}
