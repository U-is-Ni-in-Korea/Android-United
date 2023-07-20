package sopt.uni.data.entity.home

data class HomeInfo(
    val roundGameId: Int? = 0,
    val myScore: Int? = 0,
    val partnerScore: Int? = 0,
    val drawCount: Int? = 0,
    val dDay: Int? = 0,
    val enable: Boolean? = false,
)
