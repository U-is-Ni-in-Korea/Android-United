package sopt.uni.data.source.remote.request

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class RequestPostCheckConnectionDto(
    @SerialName
    ("inviteCode")
    val inviteCode: String,
)
