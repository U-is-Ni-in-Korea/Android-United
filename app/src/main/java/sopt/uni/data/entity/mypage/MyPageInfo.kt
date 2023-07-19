package sopt.uni.data.entity.mypage

data class MyPageInfo(
    val couple: Couple,
    val id: Int,
    val image: String,
    val nickname: String,
) {
    data class Couple(
        val heartToken: Int,
        val id: Int,
        val inviteCode: String,
        val startDate: String,
    )
}
