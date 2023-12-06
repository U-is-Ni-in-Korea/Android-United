package sopt.uni.presentation.common.content

sealed class ErrorCodeState {
    object NoError : ErrorCodeState()
    object NoToken : ErrorCodeState()
}
