package sopt.uni.data.entity.shortgame

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class ResponseShortGameResult(
    @SerialName("myRoundMission")
    val myRoundMission: RoundMission,
    @SerialName("partnerRoundMission")
    val partnerRoundMission: RoundMission?,
)
