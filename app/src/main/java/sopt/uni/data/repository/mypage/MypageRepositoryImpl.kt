package sopt.uni.data.repository.mypage

import sopt.uni.data.entity.history.MyPage
import sopt.uni.data.service.MyPageService
import sopt.uni.data.source.remote.response.MyPageResponseDto
import javax.inject.Inject

class MypageRepositoryImpl @Inject constructor(
    private val mypageService: MyPageService,
) : MypageRepository {

    override suspend fun getMyPageInfo(): Result<MyPage> {
        return kotlin.runCatching {
            val myPageResponse = mypageService.getMyPageInfo()
            convertToMyPage(myPageResponse)
        }.fold(
            onSuccess = { myPage ->
                Result.success(myPage)
            },
            onFailure = { exception ->
                Result.failure(exception)
            },
        )
    }

    private fun convertToMyPage(myPageResponse: MyPageResponseDto): MyPage {
        return MyPage(
            userId = myPageResponse.userId,
            nickname = myPageResponse.nickname,
            image = myPageResponse.image,
            startDate = myPageResponse.startDate,
        )
    }
}
