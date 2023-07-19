package sopt.uni.data.entity.shortgame

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class ShortGame(
    @SerialName("enable")
    val enable: Boolean,
    @SerialName("finishAt")
    val finishAt: String?,
    @SerialName("id")
    val id: Int,
)
