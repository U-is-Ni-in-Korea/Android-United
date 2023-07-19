package sopt.uni.data.source.remote.response

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class HistoryResponseDto(
    @SerialName("date")
    val date: String,
    @SerialName("image")
    val image: String,
    @SerialName("myMission")
    val myMission: MyMission,
    @SerialName("partnerMission")
    val partnerMission: PartnerMission,
    @SerialName("result")
    val result: String,
    @SerialName("roundGameId")
    val roundGameId: Int,
    @SerialName("title")
    val title: String,
    @SerialName("winner")
    val winner: String,
) {
    @Serializable
    data class MyMission(
        @SerialName("content")
        val content: String,
        @SerialName("result")
        val result: String,
        @SerialName("time")
        val time: String,
    )

    @Serializable
    data class PartnerMission(
        @SerialName("content")
        val content: String,
        @SerialName("result")
        val result: String,
        @SerialName("time")
        val time: String,
    )
}
