package sopt.uni_aos.data.source.remote.response

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class ExampleResponseDto(
    @SerialName("id")
    val id: Int,
)