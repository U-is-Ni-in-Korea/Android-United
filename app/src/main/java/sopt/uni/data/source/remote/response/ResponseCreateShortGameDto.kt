package sopt.uni.data.source.remote.response

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import sopt.uni.data.entity.shortgame.RoundMission
import sopt.uni.data.entity.shortgame.ShortGame

@Serializable
data class ResponseCreateShortGameDto(
    @SerialName("shortGame")
    val shortGame: ShortGame,
    @SerialName("roundGameId")
    val roundGameId: Int,
    @SerialName("roundMission")
    val roundMission: RoundMission,
)
