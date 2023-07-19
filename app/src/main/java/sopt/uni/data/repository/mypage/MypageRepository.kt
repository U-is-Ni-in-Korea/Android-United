package sopt.uni.data.repository.mypage

import sopt.uni.data.entity.history.MyPage

interface MypageRepository {
    suspend fun getMyPageInfo(): Result<MyPage>
}
