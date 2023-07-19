package sopt.uni.data.source.remote.request

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class RequestCreateShortGameDto(
    @SerialName("missionCategoryId")
    val missionCategoryId: Int,
    @SerialName("wishContent")
    val wishContent: String,
)
