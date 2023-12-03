package sopt.uni.data.repository.mypage

import okhttp3.MultipartBody
import retrofit2.Response
import sopt.uni.data.entity.history.MyPage
import sopt.uni.data.entity.mypage.MyPageInfo

interface MypageRepository {
    suspend fun getMyPageInfo(): Result<MyPage>

    suspend fun sendMyPageInfo(
        image: MultipartBody.Part?,
        nickname: String,
        startDate: String,
    ): Result<MyPageInfo>

    suspend fun deleteUser(): Response<Unit>

    suspend fun disconnectCouple(): Response<Unit>
}
