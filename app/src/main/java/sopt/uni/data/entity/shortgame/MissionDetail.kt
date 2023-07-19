package sopt.uni.data.entity.shortgame

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class MissionDetail(
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
    @SerialName("image")
    val image: String,
    @SerialName("missionContentList")
    val missionContentList: List<MissionExample>,
)
