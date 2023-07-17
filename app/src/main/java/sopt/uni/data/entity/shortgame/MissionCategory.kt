package sopt.uni.data.entity.shortgame

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class MissionCategory(
    @SerialName("description")
    val description: String,
    @SerialName("expectedTime")
    val expectedTime: Int,
    @SerialName("id")
    val id: Int,
    @SerialName("image")
    val image: String,
    @SerialName("level")
    val level: Int,
    @SerialName("missionType")
    val missionType: String,
    @SerialName("tip")
    val tip: String,
    @SerialName("title")
    val title: String,
)
