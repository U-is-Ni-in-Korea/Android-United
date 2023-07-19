package sopt.uni.data.source.remote.response

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class ResponseCheckConnectionDto(
    @SerialName("connection")
    val connection: Boolean,
)
