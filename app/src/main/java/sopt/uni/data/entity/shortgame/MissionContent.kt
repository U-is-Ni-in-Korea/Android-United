package sopt.uni.data.entity.shortgame

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class MissionContent(
    @SerialName("content")
    val content: String,
    @SerialName("id")
    val id: Int,
    @SerialName("recommendTime")
    val recommendTime: String?,
    @SerialName("missionCategory")
    val missionCategory: MissionCategory,
)
