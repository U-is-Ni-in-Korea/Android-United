package sopt.uni.data.datasource.remote

import okhttp3.Interceptor
import okhttp3.Request
import okhttp3.Response
import sopt.uni.data.datasource.local.SparkleStorage

class AuthInterceptor() : Interceptor {
    override fun intercept(chain: Interceptor.Chain): Response {
        val accessToken = SparkleStorage.accessToken

        val originalRequest = chain.request()

        if (shouldSkipIntercept(originalRequest)) {
            return chain.proceed(originalRequest)
        }

        val headerRequest = originalRequest.newBuilder()
            .addHeader(
                "Authorization",
                if (accessToken != null) {
                    BEARER + accessToken
                } else {
                    return chain.proceed(
                        originalRequest,
                    )
                },
            )
            .build()
        return chain.proceed(headerRequest)
    }

    private fun shouldSkipIntercept(request: Request): Boolean {
        if (request.headers.size > 0) {
            return true
        }
        return false
    }

    companion object {
        private const val BEARER = "Bearer "
    }
}
