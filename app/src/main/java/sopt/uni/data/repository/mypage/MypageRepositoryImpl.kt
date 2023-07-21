package sopt.uni.data.repository.mypage

import okhttp3.MediaType.Companion.toMediaType
import okhttp3.MultipartBody
import okhttp3.RequestBody.Companion.toRequestBody
import sopt.uni.data.entity.history.MyPage
import sopt.uni.data.entity.mypage.MyPageInfo
import sopt.uni.data.service.MyPageService
import sopt.uni.data.source.remote.response.MyPageInfoResponseDto
import sopt.uni.data.source.remote.response.MyPageResponseDto
import timber.log.Timber
import java.text.SimpleDateFormat
import java.util.Locale
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

    override suspend fun sendMyPageInfo(
        image: MultipartBody.Part?,
        nickname: String,
        startDate: String,
    ): Result<MyPageInfo> {
        val parsedStartDate = parseCoupleDate(startDate)
        return kotlin.runCatching {
            val nicknameRequestBody = nickname.toRequestBody("text/plain".toMediaType())
            val startDateRequestBody = parsedStartDate.toRequestBody("text/plain".toMediaType())
            val myPageInfoResponse =
                mypageService.sendMyPageInfo(null, nicknameRequestBody, startDateRequestBody)
            convertToMyPageInfo(myPageInfoResponse)
        }.fold(
            onSuccess = { response ->
                Result.success(response)
            },
            onFailure = { exception ->
                Result.failure(exception)
            },
        )
    }

    private fun parseCoupleDate(coupleDate: String): String {
        try {
            val inputFormat = SimpleDateFormat("yyyy년 M월 d일", Locale.getDefault())
            val outputFormat = SimpleDateFormat("yyyy-MM-dd", Locale.getDefault())
            val date = inputFormat.parse(coupleDate)
            return outputFormat.format(date)
        } catch (e: Exception) {
            Timber.e(e, "parseCoupleDate 에러")
            throw e
        }
    }

    private fun convertToMyPage(myPageResponse: MyPageResponseDto): MyPage {
        return MyPage(
            userId = myPageResponse.userId,
            nickname = myPageResponse.nickname,
            image = myPageResponse.image,
            startDate = myPageResponse.startDate,
        )
    }

    private fun convertToMyPageInfo(myPageInfoResponse: MyPageInfoResponseDto): MyPageInfo {
        return MyPageInfo(
            couple = MyPageInfo.Couple(
                heartToken = myPageInfoResponse.couple.heartToken,
                id = myPageInfoResponse.couple.id,
                inviteCode = myPageInfoResponse.couple.inviteCode,
                startDate = myPageInfoResponse.couple.startDate,
            ),
            nickname = myPageInfoResponse.nickname,
            image = myPageInfoResponse.image,
            id = myPageInfoResponse.id,
        )
    }
}
