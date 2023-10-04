package sopt.uni.data.entity.shortgame

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class MissionCategory(
    @SerialName("expectedTime")
    val expectedTime: Int,
    @SerialName("id")
    val id: Int,
    @SerialName("title")
    val title: String,
    @SerialName("description")
    val description: String,
    @SerialName("rule")
    val rule: String,
    @SerialName("tip")
    val tip: String,
    @SerialName("example")
    val example: String,
    @SerialName("image")
    val image: String,
    @SerialName("level")
    val level: Int,
    @SerialName("missionType")
    val missionType: String,
    @SerialName("missionTool")
    val missionTool: String,
)
