package sopt.uni.data.source.remote.response

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import sopt.uni.data.entity.shortgame.RoundMission

@Serializable
data class ResponseShortGameResultDto(
    @SerialName("myRoundMission")
    val myRoundMission: RoundMission,
    @SerialName("partnerRoundMission")
    val partnerRoundMission: RoundMission? = null,
)
