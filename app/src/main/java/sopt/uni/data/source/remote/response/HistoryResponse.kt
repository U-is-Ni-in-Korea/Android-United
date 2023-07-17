package sopt.uni.data.source.remote.response

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class HistoryResponse(
    @SerialName("roundGameId")
    val roundGameId: Int,
    @SerialName("date")
    val date: String,
    @SerialName("result")
    val result: String,
    @SerialName("title")
    val title: String,
    @SerialName("image")
    val image: String,
) {
    @Serializable
    data class MyMission(
        @SerialName("nickname")
        val nickname: String,
        @SerialName("result")
        val result: String,
        @SerialName("time")
        val time: String,

    )

    @Serializable
    data class PartnerMission(
        @SerialName("nickname")
        val nickname: String,
        @SerialName("result")
        val result: String,
        @SerialName("time")
        val time: String,
    )
}
