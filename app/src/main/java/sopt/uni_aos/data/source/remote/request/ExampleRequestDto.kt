package sopt.uni_aos.data.source.remote.request

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class ExampleRequestDto(
    @SerialName("id")
    val id: String,
    @SerialName("type")
    val type: String,
)
