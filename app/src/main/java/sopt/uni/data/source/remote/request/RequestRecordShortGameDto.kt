package sopt.uni.data.source.remote.request

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class RequestRecordShortGameDto(
    @SerialName("result")
    val result: Boolean
)
