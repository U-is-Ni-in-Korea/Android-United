package sopt.uni.data.entity.shortgame

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class RoundMission(
    @SerialName("finalResult")
    val finalResult: String,
    @SerialName("id")
    val id: Int,
    @SerialName("missionContent")
    val missionContent: MissionContent,
    @SerialName("result")
    val result: String,
    @SerialName("updatedAt")
    val updatedAt: String?,
)
