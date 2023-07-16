package sopt.uni.data.datasource.remote

import sopt.uni.data.service.AuthService
import sopt.uni.data.source.remote.request.RequestAuthDto
import sopt.uni.data.source.remote.response.ResponseAuthDto
import javax.inject.Inject

class AuthDatasource @Inject constructor(
    private val authService: AuthService,
) {
    suspend fun getToken(social: String, requestAuthDto: RequestAuthDto): ResponseAuthDto =
        authService.getToken(social, requestAuthDto)
}
