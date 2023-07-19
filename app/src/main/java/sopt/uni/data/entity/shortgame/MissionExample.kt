package sopt.uni.data.entity.shortgame

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class MissionExample(
    @SerialName("id")
    val id: Int,
    @SerialName("content")
    val content: String,
)
